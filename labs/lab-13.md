# 🖥 Lab 13: Saving User Data Using DataStore
Let's save the last selected note category and default to that whenever the `CreateNoteFragment` is displayed

## Objectives

0. Integrate Proto DataStore into the project (already done in starter code)
    1. Apply the `com.google.protobuf` plugin to your project (see hints)
    2. Add the DataStore dependency to your project (see hints)
    3. Add `protobuf{}` config block `app/build.gradle` (see hints)
    
1. Add the Data Store dependency `implementation "androidx.datastore:datastore:1.0.0"`    
    
2. Define protobuf message to represent the default note category
    1. Create a protobuf file named `DefaultCategory.proto` file at `app/src/main/proto/DefaultCategory.proto`
    2. Within `DefaultCategory.proto`, define a `DefaultCategory` proto message with a single `string category = 1;` field (see hints)

3. Prepare DataStore for interaction
    1. Create a new package named `datastore`
    2. Create `DefaultCategorylSerializer` class that extends `Serializer<DefaultCategory>` (see hints)
    3. Create `defaultCategoryDataStore` extension property (see hints)


4. Create a `DefaultCategoryRepository` inteface (see hints)
    1. Should have a property named `defaultCategory` with type `Flow<DefaultCategory>`
    2. Should have a suspending function named `updateDefaultCategory` that takes a `String` parameter `category`.

5. Create a class `DataStoreCategoryRepository` that implements `DefaultCategoryRepository` (see hints)
    1. The class should have a single constructor parameter `private val context: () -> Context`
    2. Implement `defaultCategory` using a computed property `get() = context().defaultCategoryDataStore.data`
    3. Implement `updateDefaultCategory()` to use our extention property `defaultCategoryDataStore` to update the currently saved `DefaultCategory`

6. Pass an instance of `DefaultCategoryRepository` to `CreateNoteViewModel`
    1. Add a constructor property to `CreateNoteViewModel` of type `DefaultCategoryRepository`
    2. `CreateNoteViewModelFactory` should take an instance of `DefaultCategoryRepository` and pass it to the view model constructor
    3. In `CreateNoteFragment`, create an instance of `DataStoreCategoryRepository` and pass it to the instance of `CreateNoteViewModelFactory`

7. Add methods to `CreateNoteViewModel` for handling the `DefaultCategory`
    1. Add a method `suspend fun saveSelectedCategory(selectedIndex: Int)` that calls `categoryRepository.updateDefaultCategory(CATEGORIES[selectedIndex])`
    2. Add a method `fun indexForCategory(category: String): Int = CATEGORIES.indexOf(category)`

4. Update category spinner to respond to selection and to set default value
    1. In `CreateNoteFragment.onCreateView()`, add a `OnItemSelectedListener` to your spinner
    4. In `onItemSelected` launch a coroutine and call `viewModel.saveSelectedCategory(position)`
    5. In `CreateNoteFragment.onViewCreated()` collect `cateogryRepository.defaultCategory` and call `binding.categorySpinner.setSelection(viewModel.indexForCategory(category.category))`

# 🖥 Lab 13 Hints: Saving User Data Using DataStore

## 💡 Helpful Resources
- [DataStore Getting Started Guide](https://developer.android.com/topic/libraries/architecture/datastore#prefs-vs-proto)
- [What are Protocol Buffers?](https://developers.google.com/protocol-buffers)
- [Protocol Buffer (proto3) Language Guide](https://developers.google.com/protocol-buffers/docs/proto3)

## 💡 How do I setup protobuf code generation to work with DataStore
This bit isn't obvious from the documentation, and isn't part of the main focus of this workshop, so feel free to follow these setups at the start of this lab.

First, you need to add the following plugin
```groovy
// within app/build.gradle

plugins {
    ...
    id "com.google.protobuf" version "0.8.12"
}
```

Next, you need to add the dependency for the plugin
```groovy
// within app/build.gradle

dependencies {
    ...
    implementation  "com.google.protobuf:protobuf-javalite:3.11.0"
}
```

Finally, add the following after the `dependencies{}` block
```groovy
// within app/build.gradle

/**
 * Sets up protobuf code generation so we can generate DataStore models from our protobuf messages
 */
protobuf {
  protoc {
    artifact = "com.google.protobuf:protoc:3.11.0"
  }

  generateProtoTasks {
    all().each { task ->
      task.builtins {
        java {
          option 'lite'
        }
      }
    }
  }
}
```

## 💡 Which DataStore dependency do I need?
For this lab, you should only need ` implementation "androidx.datastore:datastore:1.0.0"`

## 💡 What should DefaultCategory.proto look like?
```
syntax = "proto3";

option java_package = "dev.goobar.androidstudyguide.protos";   <-- update this with the package name of your project
option java_multiple_files = true;

message DefaultCategory {
  string category = 1;
}
```

## 💡 How do I implement DefaultCategorySerializer?
You'll need to implement the `datastore.core.Serializer` interface with a type value of `DefaultCategory`.
```kotlin
object DefaultCategorySerializer : Serializer<DefaultCategory> {
  override val defaultValue: DefaultCategory = DefaultCategory.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): DefaultCategory {
    try {
      return DefaultCategory.parseFrom(input)
    } catch (exception: InvalidProtocolBufferException) {
      throw CorruptionException("Cannot read proto.", exception)
    }
  }

  override suspend fun writeTo(t: DefaultCategory, output: OutputStream) = t.writeTo(output)
}
```

## 💡 How do I interact with my DataStore?
A convenient way of interacting with a DataStore is to create an extension property on the `Context` or `Activity
class.  This lets us interact with DataStore any time we have access to a `Context` or `Activity.

```kotlin
val Context.defaultCategoryDataStore: DataStore<DefaultCategory> by dataStore(fileName = "notes.pb", serializer = DefaultCategorySerializer)
```

## 💡 How to implement DefaultCategoryRepository
```kotlin
interface DefaultCategoryRepository {
  val defaultCategory: Flow<DefaultCategory>
  suspend fun updateDefaultCategory(category: String)
}

class DataStoreCategoryRepository(private val context: () -> Context) : DefaultCategoryRepository {

  override val defaultCategory: Flow<DefaultCategory>
    get() = context().defaultCategoryDataStore.data

  override suspend fun updateDefaultCategory(category: String) {
    context().defaultCategoryDataStore.updateData { defaultCategory ->
      defaultCategory.toBuilder().setCategory(category).build()
    }
  }

}
```

## 💡 How do I respond to Spinner selection?
`Spinners` are a form of `AdapterView` and therefore support setting a `OnItemSelectedListener` to be notified anytime a user selects an item

[Check out the documentation for more info](https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener.html)

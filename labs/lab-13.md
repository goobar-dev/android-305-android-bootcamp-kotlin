# 🖥 Lab 13: Saving User Data Using DataStore
Let's save the last selected note category and default to that whenever the `CreateNoteFragment` is displayed

## Objectives
1. Integrate Proto DataStore into the project
    1. Apply the `com.google.protobuf` plugin to your project (see hints)
    2. Add the DataStore dependency to your project (see hints)
    3. Add `protobuf{}` config block `app/build.gradle` (see hints)
2. Define protobuf message to represent the default note category
    1. Create a protobuf file at `app/src/main/proto/DefaultCategory.proto`
    2. Within `DefaultCategory.proto`, define a `DefaultCategory` proto message with a single `string category = 1;` field
3. Prepare DataStore for interaction
    1. Create `DefaultCategorylSerializer` class that extends `Serializer<DefaultCategory>` (see hints)
    2. Create `defaultCategoryDataStore` extension property (see hints)
4. Update category spinner to respond to selection and to set default value
    1. Refactor `CategorySpinnerAdapter` to take a list of category `String`s in its constructor
    2. Create a top-level property to store a `List<String>` of category `String`s to pass to the adapter
    3. In `CreateNoteFragment.onCreateView()`, add a `OnItemSelectedListener` to your spinner
    4. Save the selected category to your `DataStore` each time the spinner is selected
    5. In `CreateNoteFragment.onViewCreated()` query `defaultCategoryDataStore` for the last used category and select that item in the current spinner

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
For this lab, you should only need ` implementation "androidx.datastore:datastore:1.0.0-rc01"`

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

## 💡 How do I respond to Spinner selection?
`Spinners` are a form of `AdapterView` and therefore support setting a `OnItemSelectedListener` to be notified anytime a user selects an item

[Check out the documentation for more info](https://developer.android.com/reference/android/widget/AdapterView.OnItemSelectedListener.html)
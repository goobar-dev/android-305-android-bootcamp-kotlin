# 🖥 Lab 14: Building a CRUD Working Using Room
Let's add support for creating, retrieving, updating, and deleting custom notes.

## Objectives 1

1. Update `Note` model to be a Room `@Entity`
    1. Can remove the @Parcelize setup as we will no longer pass `Note` directly but instead load it from DB
    2. Add a class-body `var` property named `id` of type `Int` with default value `0`
    3. Annotate `id` with `@PrimaryKey(autoGenerate = true)`

2. Create a new package `db`

3. Within the `db` package, create a `NoteDao` interface with the following methods
    1. `fun getAll(): Flow<List<Note>>`
    2. `suspend fun get(noteId: Int): Note`
    3. `suspend fun insert(note: Note)`
    4. `suspend fun update(note: Note)`
    5. `suspend fun delete(note: Note)`
    6. You will need to annotate these methods with the appropriate Room annotations

4. Within the `db` package, create an `AppDatabase` class to access `NoteDao` and interact with the database

5. Create a custom `Application` class named `AndroidStudyGuideApplication`
    1. Name the new class `AndroidStudyGuideApplication`
    2. It should extend `Application`
    3. Within `AndroidManifest.xml` add `android:name=".AndroidStudyGuideApplication"` to the `<application>` element
    4. This will result in our custom `Application` class being instantiated when our app is started

6. Make `AppDatabase` globally available by creating a public, lazy property on the `AndroidStudyGuideApplication` class

7. Create an extension function on `Activity` for easy access to `AndroidStudyGuideApplication`
    1. This will require casting `Activity.application` to `AndroidStudyGuideApplication`

## Objectives 2
Once you have access to the database, it's time to implement our CRUD workflow across our application.
We'll update our app to support the following:
- Displaying saved notes (from `MyNotesFragment`)
- Saving notes to the database (from `CreateNoteFragment`)
- Viewing saved note details (from `NoteDetailsFragment`)

1. Update `CreateNoteFragment` and `CreateNoteViewModel` to save a `Note` to the database
    1. Update `CreateNoteViewModel` to take an instance of `NoteDao` as a parameter
    2. Add a method to `CreateNoteViewModel` named `fun save(title: String, categoryIndex: Int, content: String)` and implement using `NoteDao`
    3. In `CreateNoteFragment`, call `CreateViewModel.save()` when the save button is clicked
    
3. Update `MyNotesFragment` and `MyNotesViewModel` to populate `MyNotesListAdapter` based on items saved to the database
    1. Update `MyNotesViewModel` to take an instance of `NoteDao` as a parameter
    2. Refactor the implementation of the `state` property to instead use `noteDao.getAll().map { UiState(it) }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState()`

3. Update `NoteDetailFragment` and `NoteDetailViewModel` to load `Note` data from the database based on a passed note id
    1. Update `NoteDetailViewModel` to take an `id` instead of a `Note`
    2. Update `NoteDetailViewModel` to take an instance of `NoteDao`
    3. In the `init{}` block of `NoteDetailViewModel`, launch a new coroutine on a background thread
    4. Within the launched coroutine, call `noteDao.getNote(id)`
    5. Update the `UiState` using the loaded `Note`


## Challenges

1. Add the ability to delete a note
2. Add the ability to edit a note


# 🖥 Lab 14: Building a CRUD Working Using Room

## 💡 Helpful Resources
- [Save data in a local database using Room](https://developer.android.com/training/data-storage/room)

## 💡 How do I create a lazy property for accessing `AppDatabase`?
```kotlin
class AndroidStudyGuideApplication : Application() {

  val database: AppDatabase by lazy {
    Room.databaseBuilder(this, AppDatabase::class.java, "app-database").build()
  }

}
```

## 💡 How can we use a data class for our Room data model, while also having an auto-incrementing id property?
If we want our Room model data class to have an auto-incrementing id that is not managed by us, then we can't put the property in the constructor.
But if we can't put the data class property in the constructor, where can we put it?

Well, we can still add properties to a data class that are outside the constructor.
The only caveat, is that those properties won't be considered as part of the auto-generated equals/hashcode & copy() methods

```kotlin
@Entity
data class Note(
  @ColumnInfo(name = "title") val title: String,
  @ColumnInfo(name = "category") val category: String,
  @ColumnInfo(name = "content") val content: String
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}
```

## 💡 If we are using suspend, and Flow as part of our Dao, how do we call those methods from a Fragment?
Because `suspend` and `Flow` are part of the Kotlin coroutines package, we must sometimes call those methods from a coroutine.

From within a Fragment, the best place to start is to call `lifecycleScope.launch {}` and perform any coroutine executions within the `launch{}` lambda.
We'll dive deeper into coroutines in Day 4.

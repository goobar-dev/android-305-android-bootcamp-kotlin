# 🖥 Lab 14: Building a CRUD Working Using Room
Let's add support for creating, retrieving, updating, and deleting custom notes.

## Objectives
1. Add Room dependencies (see hints)
2. Update `Note` model to be a Room `@Entity`
    1. Can remove the @Parcelize setup as we will no longer pass `Note` directly but instead load it from DB
3. Create `NoteDao` interface with the following methods
    1. `fun getAll(): Flow<List<Note>>`
    2. `suspend fun get(noteId: Int): Note`
    3. `suspend fun insert(note: Note)`
    4. `suspend fun update(note: Note)`
    5. `suspend fun delete(note: Note)`
    6. You will need to annotate these methods with the appropriate Room annotations
4. Create `AppDatabase` class to access `NoteDao` and interact with the database
5. Make `AppDatabase` globally available by creating a public, lazy property on the `AndroidStudyGuideApplication` class
6. Create an extension function on `Activity` for easy access to `AndroidStudyGuideApplication`
    1. This will require casting `Activity.application` to `AndroidStudyGuideApplication

Once you have access to the database, it's time to implement our CRUD workflow across our application.
We'll update our app to support the following
- Displaying saved notes (from `MyNotesFragment`)
- Saving notes to the database (from `CreateNoteFragment`)
- Deleting a saved note (from `NoteDetailFragment`)
- Editing a saved note (from `CreateNoteFragment`)

7. Update `MyNotesFragment` to populate `MyNotesListAdapter` based on items saved to the database
8. Update `CreateNoteFragment` to save a `Note` to the database
9. Update `NoteDetailFragment` to load `Note` data from the database based on a passed note id
10. Update `NoteDetailFragment` to include a "Delete" and an "Edit" button
    1. "Delete" should delete the current `Note` from the database and return to the home screen
    2. "Edit" should navigate to `CreateNoteFragment` and pass `Note.id` so `CreateNoteFragment` can update the current note when saved
11. Update `CreateNoteFragment` to support loading a `Note` based on id and updating that `Note` when save is clicked

## Challenges

### Update Back navigation
Update the navigation from `NoteDetailFragment` to `CreateNoteFragment`.
When you hit back from `CreateNoteFragment` the user should return to the home screen rather than to `NoteDetailFragment`.


# 🖥 Lab 14: Building a CRUD Working Using Room

## 💡 Helpful Resources
- [Save data in a local database using Room](https://developer.android.com/training/data-storage/room)

## 💡 What dependencies will I need for this lab?
Room has a lot of optional dependencies depending on what functionality you want/need.

For this lab, you should only need the following additions
```groovy
plugins {
   ...
    id "org.jetbrains.kotlin.kapt"
}

// Room dependencies
def room_version = "2.4.2"

implementation("androidx.room:room-runtime:$room_version")
kapt("androidx.room:room-compiler:$room_version")
implementation("androidx.room:room-ktx:$room_version")
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
  @ColumnInfo(name = "content") val content: String,
  @ColumnInfo(name = "imageUri") val imageUri: String? = null
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}
```

## 💡 If we are using suspend, and Flow as part of our Dao, how do we call those methods from a Fragment?
Because `suspend` and `Flow` are part of the Kotlin coroutines package, we must sometimes call those methods from a coroutine.

From within a Fragment, the best place to start is to call `lifecycleScope.launch {}` and perform any coroutine executions within the `launch{}` lambda.
We'll dive deeper into coroutines in Day 4.

## 💡 My app crashes when trying to display imageUri on app restart
As part of Android's recent permissions updates, new versions of Android must request persistent permissions to Uris from public folders.

This means, that after you retrieve an image Uri in `onActivityResult()` you must call the following code so that your app will still have permission to view the file on next launch

```kotlin
// refreshes permissions for the image URI
// required on Android 11+ due to scoped storage changes
val contentResolver = requireContext().contentResolver
val takeFlags: Int = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
contentResolver.takePersistableUriPermission(uri, takeFlags)
```
# 🖥 Lab 11: Displaying List Data with RecyclerView
Let’s update our app to display a hardcoded list of data in `MyNotesFragment`

## Objectives

1. Remove `textView` and `noteDetailButton` from `fragment_my_notes.xml`

2. Add a `RecyclerView` to `fragment_my_note.xml` and constrain it to fill the screen.
    1. The `FloatingActionButton` should still be visible on top of the `RecyclerView`

3. Create `item_note.xml` to represent individual `Note` list items in the UI.  The layout should include:
    1. An `ImageView` with id `noteImage`
    2. A `TextView` with id `titleTextView`
    3. A `TextView` with id `categoryText`
    4. A `TextView` with id `contentText`

4. Create a file named `MyNotesListAdapter.kt` within the `mynotes` package

5. Within `MyNotesListAdapter.kt`, create a `NoteViewholder` class that binds view references from `item_note.xml` and bind `Note`s into the view
    1. `NoteViewHolder` should extend `RecyclerView.ViewHolder`
    2. Pass an instance of `ItemNoteBinding` as a constructor property to `NoteViewHolder` and pass `binding.root` to the `RecyclerView.ViewHolder` constructor
    3. Create a `bindNote(note: Note)` method that takes a note, and sets that data into the `View` references

6. Create an oject class `NoteDiffUtil` that extends `DiffUtil.ItemCallback<Note>`
    1. Override both `areItemsTheSame()` and `areContentsTheSame()` to perform equality comparison of two `Note`s

7. Create a `MyNotesListAdapter` class to convert a `List<Note>` into views for the `RecyclerView`

8. Within `MyNotesFragment`, set a `LinearLayoutManager` to your `RecyclerView`

9. Within `MyNotesFragment`, create an instance of `MyNotesListAdapter` and store it as a class property named `notesAdapter`

10. Refactor `MyNotesViewModel.UiState.notes` to be a `List<Note>` rather than `List<String>`

11. Call `notesAdapter.submitList(uiState.notes)` within the collection of `MyNotesViewModel.state`

12. Add an `init{}` block within `MyNotesViewModel` and update the `UiState` to include the data from `SAMPLE_NOTES`
    1. `state.update { currentState -> currentState.copy(notes = SAMPLE_NOTES) }`

13. Redploy your app and observe the list of `Note`s

10. Respond to list item selections by showing `NoteDetailFragment`
    1. Pass a function to your `MyNotesListAdapter` to respond to list item clicks.  The function should take a `Note` as a parameter and return `Unit`
    2. In `MyNotesListAdapter,onBindViewHolder()` add a click listener to the item view by calling `setOnClickListener{}`
    3. Within the `View`'s click listener, call back into the click listener passed to the adapter

11. Pass the selected `Note` to `NoteDetailFragment` when item is selected
    1. Add `id 'kotlin-parcelize'` to the `plugins{}` block of `app/build.gradle`
    2. Update `Note` to implement `Parcelable` and add `@Parcelize` annotation to the class
    4. Open `main_navigation.xml` and add an argument to `NoteDetailsFragment` named `selectedNote` of type `Note`
    5. Rebuld the project to generate a navigation action class
    6. Within the click handler passed to `MyNotesAdapter` navigate to `NoteDetailsFragment` using `findNavController().navigate(MyNotesFragmentDirections.actionMyNotesFragmentToNoteDetailsFragment(note))`

12. Display the displayed `Note` data in `NoteDetailsFragment`
    1. Within `NoteDetailsFragment`, we can access the passed arguments using `private val args: NoteDetailsFragmentArgs by navArgs()`
    2. We then will create a class named `NoteDetailsViewModelFactory` that takes the selected note as a constructor property and implements `ViewModelProvider.Factory`
    3. Add a constructor property named `note` to `NoteDetailsViewModel` with type `Note`
    4. Override `create()` within `NoteDetailsViewModelFactory` to return an instance of `NoteDetailsViewModel`; passing the selected `Note`
    5. Within `NoteDetailsFragment` update the call to `by viewModels()` by passing `factoryProducer = { NoteDetailsViewModelFactory(args.selectedNote) }`.  This will pass the selected `Note` to our view model when it's accessed for the first time.
    6. In `NoteDetailsViewModel` update the `state` to pull its data from the passed `Note`


## Challenges

1. Update `StudyGuideFragment` to display a list of `Note`


# 🖥 Lab 11 Hints: Displaying List Data with RecyclerView

## 💡 Helpful Resources
- [Create dynamic lists with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Efficient RecyclerView Updates with ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
- [Customizing list item touch feedback](https://developer.android.com/training/material/animations#Touch)
- [GridLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/GridLayoutManager)
- [Efficient data serialization with Parcelable](https://developer.android.com/reference/android/os/Parcelable)
- [Simplify Parcelable implementation with @Parcelize Plugin](https://developer.android.com/kotlin/parcelize)
- [Type-safe arguments with Android Navigation component](https://developer.android.com/guide/navigation/navigation-pass-data#groovy)

## 💡 How to add a simple ripple animation when I select my list item?
Try adding `android:foreground="?attr/selectableItemBackground"` to the root view of your list item layout

## 💡 How to show my list of data in a grid rather that list?
`RecyclerView` supports drawing different configurations by using `LayoutManagers`.

Take a look at `GridLayoutManager` to draw items in a grid

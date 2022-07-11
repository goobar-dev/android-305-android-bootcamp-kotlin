# 🖥 Lab 11: Displaying List Data with RecyclerView
Let’s update our app to display a hardcoded list of data in `MyNotesFragment`

## Objectives
1. Define a data class to represent a `Note`
2. Remove `noteDetailButton` from `fragment_my_notes.xml`
3. Add a `RecyclerView` to `fragment_my_note.xml`
4. Get a reference to your `RecyclerView` in `MyNotesFragment`
5. Create `item_note.xml` to represent individual `Note` list items in the UI
6. Create a `NoteViewholder` class that will bind view references from `item_note.xml` and bind `Note`s into the view
7. Create a `MyNotesListAdapter` class to convert a `List<Note>` into views for the `RecyclerView`
8. Set a `LinearLayoutManager` to your `RecyclerView`
9. Create an instance of `MyNotesListAdapter` and set it into your `RecyclerView`
10. Respond to list item selections by showing `NoteDetailFragment`
    1. Pass a function to your `MyNotesListAdapter` to respond to list item clicks
    2. In `MyNotesListAdapter,onBindViewHolder()` add a click listener to the item view by calling `setOnClickListener{}`
    3. Within the `View`'s click listener, call back into the click listener passed to the adapter
11. Pass the selected `Note` to `NoteDetailFragment` when item is selected
    1. Add `id 'kotlin-parcelize'` to the `plgins{}` block of `app/build.gradle`
    2. Update `Note` to implement `Parcelable` and add `@Parcelize` annotation to the class
    3. Update the signature of your adapter's click listener function so that it takes a single `Note` argument
    4. Within `MyNotesListAdapter,onBindViewHolder()` update the `ViewHolder`'s click listener to pass the selected `Note` to the click listener by using the selected item position to lookup the `Note` from the list passed to the adapter
    5.

## Challenges
1. Set list layout preview in `fragment_my_notes.xml`
2. Update `NoteDetailFragment` to display passed `Note` data
3. Update `NoteViewHolder` to display all `Note` data
4. Add touch feedback to your list items

# 🖥 Lab 11 Hints: Displaying List Data with RecyclerView

## 💡 Helpful Resources
- [Create dynamic lists with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
- [Efficient RecyclerView Updates with ListAdapter](https://developer.android.com/reference/androidx/recyclerview/widget/ListAdapter)
- [Customizing list item touch feedback](https://developer.android.com/training/material/animations#Touch)
- [GridLayoutManager](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/GridLayoutManager)
- [Efficient data serialization with Parcelable](https://developer.android.com/reference/android/os/Parcelable)
- [Simplify Parcelable implementation with @Parcelize Plugin](https://developer.android.com/kotlin/parcelize)

## 💡 How to pass a Note to my NoteDetailsFragment?
We can pass a `Bundle` to any call to `NavController.navigate()`.
Within that bundle, we can include simple arguments, or more complex types that implement `Parcelable`.

Because our `Note` class implements `Parcelable` we can add it to a `Bundle`, pass it to `navigate()` and then access the `Note` from `NoteDetailsFragment` when it's created.

To help create this `Bundle` we can add a companion object to `NoteDetailsFragment` that includes a `buildBundle(note: Note)` method.
We can then use the returned `Bundle` to pass to `navigate()`.

```kotlin
companion object {
    private const val EXTRA_NOTE = "note"

    fun buildBundle(note: Note) = Bundle().apply {
      putParcelable(EXTRA_NOTE, note)
    }

    private fun parseBundle(bundle: Bundle): Note {
      val note: Note? = bundle.getParcelable(EXTRA_NOTE)
      checkNotNull(note)
      return note
    }
  }
```

## How to access arguments passed to a Fragment?
To access the arguments passed to a `Fragment` call `requireArguments()` within `Fragment.onCreateView()`
You can then access individual passed arguments by calling typed getters such as `getParcelable("key")`

```kotlin
val note: Note? = requireArguments().getParcelable(EXTRA_NOTE)
```

## 💡 How to add a simple ripple animation when I select my list item?
Try adding `android:foreground="?attr/selectableItemBackground"` to the root view of your list item layout

## 💡 How to show my list of data in a grid rather that list?
`RecyclerView` supports drawing different configurations by using `LayoutManagers`.

Take a look at `GridLayoutManager` to draw items in a grid
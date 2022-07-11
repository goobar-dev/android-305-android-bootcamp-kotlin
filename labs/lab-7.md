# 🖥 Lab 7: Building a Complex User Interface

## Objectives
1. Add a custom `Toolbar` to `MainActivity`
    1. Change `Theme.AndroidStudyGuide` to extend `DarkActionBar` to `NoActionBar`
    2. Add a `Toolbar` `View` to `activity_main.xml`
    3. Set the `Toolbar` title to `"Android Study Guide"`
2. Add an overflow menu to the `Toolbar` that includes an option for showing `TwitterActivity`
    1. Create a new `Menu` resource named `main_toolbar.xml`
    2. Call `Toolbar.inflatMenu()` to add the `Menu` to the `Toolbar`
    3. Implement `Toolbar.setOnMenuItemClickListener()` to respond to clicking the menu item by showing `TwitterActivity`
    4. Remove the previously added Twitter button
3. Update `MainActivity` to use a `BottomNavigationView` to display `MyNotesFragment` and `StudyGuideFragment`
    1. Add `BottomNavigationView` to bottom of `activity_main.xml`
    2. Create a new `Menu` resource named `navigation_items.xml` that includes 2 items; 1 for "Notes" and 1 for "Guide"
    3. Set the items into the `BottomNavigtionView` using `app:menu="@menu/navigation_items"`
    4. Re-constrain `fragmentContainer` so that if fits between the `Toolbar` and `BottomNavigationView`
    5. In `MainActivity`, implement `BottomNavigationView.setOnItemSelectedListener()` to show `MyNotesFragment` and `StudyGuideFragment` when the corresponding tab is clicked
    6. Ensure `MyNotesFragment` is initially shown by calling `bottomNav.selectedItemId = R.id.myNotes` after setting up the item click listener
4. Prepare `MyNotesFragment` for future interactions
    1. Add a `FloatingActionButton` that shows `CreateNoteFragment` when clicked
    2. Add a `Button` that shows `NoteDetailFragment` when clicked
5. Now that all `Fragments` can be reached without them, remove remaining buttons from `activity_main.xml`
6. Setup basic note creation UI for `CreateNoteFragment`
    1. Add a `TextInputLayout` for collecting a note title from the user
    2. Add a `Spinner` for selecting note category and pre-populate it with you desired note categories
    3. Add a `TextInputLayout` for collecting the note content from th user
7. Setup basic note detail UI for `NoteDetailFragment`
    1. Add a `TextView` for displaying the note title
    2. Add a `TextView` for displaying the note category
    3. Add a `TextView` for displaying the note content

## Challenges
### TextInput Styling
Update your `TextInputLayout`s to use the `Outlined` theme so they look a nicer.

# 🖥 Lab 7: Building a Complex User Interface

## 💡 Helpful Resources
- [BottomNavigationView Documentation](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView)
- [Set Up the App Bar Documentation](https://developer.android.com/training/appbar/setting-up)
- [Toolbar.inflateMenu()](https://developer.android.com/reference/android/widget/Toolbar#inflateMenu(int))
- [Populating a Dropdown Spinner](https://developer.android.com/guide/topics/ui/controls/spinner)
- [TextInputLayout Documentation](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout)
- [MaterialDesign Text Fields Guidance](https://material.io/components/text-fields/android)

## 💡 Setting Toolbar title
The title of a `Toolbar` can be set programmatically or in xml.

### Via XML
`app:title="Android Study Guide"`

### Via Code
```
binding.toolbar.title = "Your Title"
```

## 💡 View is not constrained properly
Do you have a view that doesn't seem to respect the constraints you've applied to it?

Is your view filling the screen in some axis instead of restricting itself to some barrie/alignment?

This can happen if you've applied constraints, but told the view to `match_parent` along some axis. Double check that your height/width is using `0dp` size along whichever axis it should be constrained.

## 💡 Z-ordering
Views in a layout have an implicit z-ordering.  Items defined _after_ other items will draw on top of preceding items.
However, the z-ordering also takes into account any elevation applied to an element.

Take the following scenario for example:
```
<ViewGroup>
  <Material.Button>
  <FrameLayout>
</ViewGroup>
```

Which element will be drawn on top of the other?
We might thing that the FrameLayout will be on top because it's defined second.

However, because the Button View defined in the Material design component library have an elevation applied to them, they actually get drawn over the FrameLayout.

This may not be what you want/expect.  To fix this, you could show/hide the Buttons as needed, or you could apply a greater elevation to the FrameLayout so its content draws above the Button.

## 💡 Theming TextInputLayout
`TextInputLayout` comes in 2 styles:
1. `Filled`
2. `Outlined`

To change the style to `Outline` apply the following to your XML `style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox"`
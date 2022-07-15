# 🖥 Lab 7: Building a Complex User Interface

## Objectives
1. Update styling of `Toolbar`
    1. In `activity_main.xml` change `androidx.appcompat.widget.Toolbar` to `com.google.android.material.appbar.MaterialToolbar`
    2. Set `style="@style/Widget.MaterialComponents.Toolbar.Primary"`

2. Add an overflow menu to the `Toolbar` that includes an option for showing `TwitterActivity`
    1. Create a new `Menu` resource named `main_toolbar.xml`
    2. Add a menu item with `id="@+id/shareOnTwitter` and `title="Share on Twitter`
    3. Within `activity_main.xml` add `app:menu="@menu/main_toolbar"` to your `MaterialToolbar`
    4. In `MainActivity`, implement `Toolbar.setOnMenuItemClickListener()` to respond to clicking the menu item by showing `TwitterActivity`

3. Prepare `MyNotesFragment` for future interactions
    1. Change the `createNoteButton` to be a `FloatingActionButton`
    2. Reconstrain the `FloatingActionButton` to the bottom, and end, of the screen

4. Add dimension resources for spacing values
    1. Create a resources file named `dimens.xml`
    2. Create a dimen named `spacing_1x` with a value of `8dp`
    3. Create a dimen named `spacing_2x` with a value of `16dp`

5. Setup basic note creation UI for `CreateNoteFragment`
    1. Add a `TextInputLayout` for collecting a note title from the user
    2. Add a `Spinner` for selecting note category and pre-populate it with you desired note categories
    3. Add a `TextInputLayout` for collecting the note content from th user
    4. Add a `FloatingActionButton` for saving a note
    5. Convert `CreateNoteFragment` to use ViewBinding and create a class property storing a reference to the binding object
    6. Create a class `CategorySpinnerAdapter` that extends `ArrayAdapter` and populates the adapter with a `List<String>`
    7. Create a list of Strings you want to represent your note cateogries, and populate the spinner using an instance of `CategorySpinnerAdapter`

## Challenges

### Setup basic note detail UI for `NoteDetailFragment`
    1. Add a `TextView` for displaying the note title
    2. Add a `TextView` for displaying the note category
    3. Add a `TextView` for displaying the note content
    
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

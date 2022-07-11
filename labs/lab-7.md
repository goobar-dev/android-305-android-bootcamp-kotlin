# 🖥 Lab 7: Customizing the Look and Feel of Our Application
Let’s make our app look a little better by leveraging the Android resource system.

## Objectives
1. Define new Color resources to customize your app theme
2. Update the primary, and secondary colors of your app's theme using the newly created Color resources
    1. Deploy your app to observe the updated theme
3. Change your device to Dark Mode and redeploy your app
    1. Notice that it likely doesn't look how you might expect
4. Update the `values-night` version of your app's theme with your custom colors and to remove the default `Toolbar`
5. Add the following values to both the Light and Dark Themes
    1. `colorSurface`
    2. `backgroundColor`
6. Change the `android:background` attribute of each `Fragment` to pull from the Theme's `backgroundColor` property
7. Add VectorDrawable icons to your `BottomNavigationView` menu
    1. One icon for `MyNotesFragment`
    2. One icon for `StudyGuideFragment`
8. Create a new VectorDrawable icon for thee `FloatingActionButton` in `MyNotesFragment`
9. Import a custom png icon for the Twitter menu item in your `Toolbar`
    1. Can find icons here https://www.flaticon.com/
10. Replace all menu item strings with String resource values
11. Apply the following text Styles to the `TextViews` in `NoteDetailFragment`
    1. `TextAppearance.MaterialComponents.Headline3`
    2. `TextAppearance.MaterialComponents.Headline4`
    3. `TextAppearance.MaterialComponents.Body1`
12. Define the following Dimension resources and apply them, where applicable, in your `Fragments`
    1. `spacing_1x=8dp`
    2. `spacing_2x=16dp`
    3. `spacing_3x=24dp`

## Challenges
### Customize your app icon
1. Create a custom app icon using the icon creation wizard.
2. Redploy your app to observe the new icon in your launcher.

### Support landscape orientation
Provide an alternative horizontal layout for `CreateNoteFragment` and/or `NoteDetailsFragment`.

To do this, you'll need to create alternative layout resources for the `land` layout resource qualifier.

### Customize your app's typography
Customize the typography of your app by setting custom text styles in your theme.

# 🖥 Lab 7 Hints: Customizing the Look and Feel of Our Application

## 💡 Helpful Resources
- [Overview of App Resources](https://developer.android.com/guide/topics/resources/providing-resources)
- [Configuration-Based Resource Naming](https://developer.android.com/guide/topics/resources/providing-resources#QualifierRules)
- [Themes vs Styles](https://developer.android.com/guide/topics/ui/look-and-feel/themes#versus)
- [String Resources Docs](https://developer.android.com/guide/topics/resources/string-resource)
- [Color Resources Docs](https://developer.android.com/guide/topics/resources/more-resources#Color)
- [Dimension Resources Docs](https://developer.android.com/guide/topics/resources/more-resources#Dimension)
- [Style Resources Docs](https://developer.android.com/guide/topics/resources/style-resource)
- [Boolean Resources Docs](https://developer.android.com/guide/topics/resources/more-resources#Bool)
- [Drawable Resources Docs](https://developer.android.com/guide/topics/resources/drawable-resource)
- [MaterialComponents Theming Getting Started Guide](https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md)
- [Material.io Color System Guide](https://material.io/design/color/the-color-system.html#color-theme-creation)

## 💡 What other Themes are available?
There are a lot of possible Themes to choose to apply to your application.  The two flavors you're mostly likely to run across include:
1. AppCompat
2. MaterialComponents

When starting a new Android app, it's best these days to use the MaterialCompoents library, and by extension, to use `Theme.MaterialComponents` as a starting point for your own custom app theme.

Check out the `Getting started with Material Components for Android` [README](https://github.com/material-components/material-components-android/blob/master/docs/getting-started.md) for more info.

## 💡 How to pick colors for my app theme?
Check out the [Material.io Color tool](https://material.io/resources/color/#!/?view.left=0&view.right=0)

## 💡 How are my Theme colors applied to my app?
The colors applied to our application theme make up the default set of colors that our Views will pull from for their default styling.

This is especially true when using Views from the Material Components library which do a great job with default theming.

The Material.io site has some [great resources](https://material.io/develop/android/theming/color) for better understanding how individual Theme colors are used.
A few of the most common colors include
1. `colorPrimary`: _The color displayed most frequently across your app’s screens and components_
2. `colorPrimaryVariant`: _A tonal variation of the primary color. For light-mode themes, this is usually a slightly darker variant of `colorPrimary`_
3. `colorOnPrimary`: _A color that passes accessibility guidelines for text/iconography when drawn on top of the primary color._
4. `colorSecondary`: _The secondary branding color for the app, usually an accented complement to the primary branding color._

## 💡 How to get the backgroundColor property from the Theme?
If you want to use the Theme's current `backgroundColor` property for you own `View`, say for example the background of a `Fragment` container, you can reference the property as `android:background="?backgroundColor"`
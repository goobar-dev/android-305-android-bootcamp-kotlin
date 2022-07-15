# 🖥 Lab 8: Validating Inputs and Restoring State
Let's add state restoration and input validation to our app.

## Objectives
1. Write a function named `areInputsValid(): Boolean` the checks that both the title, and note, are not empty

2. Hide the Save button until all three fields have input

3. Add a `TextChangeListener` to the title text.  This listener should clear any errors on the `titleInputContainer`, and update the visibility of the save button based on whether or not the inputs are vaild

4. Add a `TextChangeListener` to the note text.  This listener should clear any errors on the `noteInputContainer`, and update the visibility of the save button based on whether or not the inputs are vaild

5. Add a click listener to the save button that shows a `Snackbar`.  When the `Snackbar` is dismissed, we should pop the current fragment off the stack

## Challenges
### Exploring state resortation
1. Within `CreateNoteFragment`, save a value to the `Bundle` in `onSaveInstanceState()`.  
2. In `onViewCreated()`, check if that value is available in the `Bundle` and show it as a `Toast`.  
3. Use the techniques we discussed to explore which situations trigger state restoration.

# 🖥 Lab 8 Hints: Validating Inputs and Restoring State

## 💡 Helpful Resources
- [Toasts Overview](https://developer.android.com/guide/topics/ui/notifiers/toasts)
- [Using Snackbars](https://developer.android.com/training/snackbar/showing#display)
- [An Overview of UI State Restoration](https://developer.android.com/topic/libraries/architecture/saving-states)
- [Options for Preserving State](https://developer.android.com/topic/libraries/architecture/saving-states#options)
- [TextInputLayout Docs](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout)

## 💡 How to show an error with TextInputLayout?
`TextInputLayout` can animate into a styled error message by setting calling `setError(msg)`.  Check out [the documentation](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout#setError(java.lang.CharSequence)) for more on customizing the error message.

## 💡 How to validate that a String is not blank?
Kotlin has a number of helpful methods for checking the contents of a `String`
- `isBlank()`
- `isEmpty()`
- `isNotBlank()`
- `isNullOrBlank()`

## 💡 How to hide a View?
You can hide a `View` by setting `view.visibility = View.GONE`.

You can show it again by setting `view.visibility = View.VISIBLE`

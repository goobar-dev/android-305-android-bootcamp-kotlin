# 🖥 Lab 8: Validating Inputs and Restoring State
Let's add state restoration and input validation to our app.

## Objectives
1. Ensure `Fragment` state is preserved on rotation by checking for empty saved state before setting initial `BottomNavigationView` item
2. Add a Save button to `CreateNoteFragment`
3. Hide the Save button until all three fields have input
4. If Save button is clicked with an invalid title, display an error using `TextInputLayout`
5. If Save button is clicked with invalid content, display an error using `TextInputLayout`
6. When Save button is clicked with valid inputs, display a "success" `Snackbar` and dismiss the `Fragment`
7. Show a `Toast` when opening `NoteDetailFragment`

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
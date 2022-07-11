# 🖥 Lab 4: Building a Game Counter App

We're going to build a simple app to count "life points" for a game.

## Objectives
1. Open your `Game Counter` project
2. Add a `TextView` to display the target point total
3. Add a `TextView` to display the user's current points
    1. This should start at 0
4. Add a +1 `Button` that the user can click to increment their score by 1
5. Every time the +1 button is clicked, the users current points label should be updated
6. When the target points total has been reached, display a feedback `Toast` to the user saying they won
7. When the target points total has been reached, display a Reset `Button`
8. When the Reset `Button` is clicked, reset the user's points to 0 and hide the Reset `Button`

## Challenges
### From `Toasts` to `Snackbars`
Replace the `Toast` with a `Snackbar`.  `Toasts` may not be seen if a user has disabled notifications for an app.
`Snackbars` are a more reliable means of displaying transient feedback to a user.

### Update score styling
Update the color of the user's current score to represent how close they are to winning.  
ex. Start with Red text, move to Yellow, and get to Green

### Support multiple users
Update the screen to support 2 users, and include which user won in the feedback `Toast`

### Add additional functionality
Add additional buttons such as +3 or -1

# 🖥 Lab 4 Hints: Building a Game Counter App

## 💡 Referencing Views from code

After we have added our views to our XML layout, we often want to work with those views from Kotlin or Java code.
To do that, we need to get a reference to those views, and we have several options for doing this.

### findViewById()
To do that from an `Activity` we can use the `findViewById()` method.

For example, let's imagine we need to access a TextView.

Make sure your XML `TextView` has an `android:id=` attribute with a valid id; say `android:id=@+id/scoreText`
Then, from your Kotlin Activity call `val myView: TextView = findViewById(R.id.scoreText)`

From there, you can interact with your `TextView` however you like.

### ViewBinding
When using `ViewBinding` the compiler generates a strongly-typed binding class for us that includes references to all the named `Views` in a layout file.

If our layout file is named `activity_main.xml`, then we can inflate our layout using `ActivityMainBinding.inflate(layoutInflater)`.
We can access the root view using `binding.root` and pass it to `setContentView()`.

We can use the binding class returned by `ActivityMainBinding.inflate(layoutInflater)` to reference our `TextView` by its id: `binding.scoreText`.

## 💡 Setting the display text in your TextView

You can do this in 2 ways:
1. from XML
2. from Code

### From XML
Use `android:text="your text here"`

### From Code
Use `yourTextView.text="your text here"` or `binding.yourTextViewId="your text here"`.

## 💡 Is your `Toast` or `Snackbar` not showing?
Make sure you call `.show()` after you've configured your `Toast` or `Snackbar`.

It should look something like:

`Toast.makeText(this, "your message", Toast.LENGTH_SHORT).show()`

## 💡 How do I change the color of my TextView?
You can use the `TextView.setTextColor()` method.  This method takes a color as an Int value.
The best way to pass this color, is to call `Activity.getColor(color resource id)`

```kotlin
someTextView.setTextColor(getColor(android.R.color.holo_green_light))
```
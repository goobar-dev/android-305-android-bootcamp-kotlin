# 🖥 Lab 17: Using Explicit and Implicit Intents

## Objectives
1. Review navigation to `TwitterActivity` via explicit intent
2. When `TwitterActivity` opens, launch an Implicit `Intent` to send a Tweet
3. Add a `Share` item to `main_toolbar.xml`
4. When `Share` is selected, use an Implicit `Intent` to send a message
5. Add support for selecting and displaying an image in `CreateNoteFragment`
    1. Within `CreateNoteFragment`, add an `ImageView` with a default svg icon
    2. When the `ImageView` is clicked, create an Implicit `Intent` to select an image`
    3. Start the image selection `Intent` with `startActivityForResult(imageSelectionIntent)`
    4. Override `CreateNoteFragment.onActivityResult()` to respond to the selected image URI
    5. Check that the `requestCode` matches the request used in `startActivityForResult()`
    6. Check that the `resultCode == Activity.RESULT_OK`
    7. If both codes look correct, access the selected image URI by calling `data.data`
    8. Use that URI to set the image into your `ImageView`
    9. Save the image URI as part of the `Note` entry in the database

## Challenges

### Update UI to display selected image
1. Update `NoteDetailFragment` UI to display the selected image
2. Change the `scaleType` on your `ImageView` so that the aspect ratio is not changed when the image URI is set

### Handling selection errors
Display feedback to the user if the "select photo" Intent returns a result other than `Activity.RESULT_OK`

# 🖥 Lab 17 Hints: Using Explicit and Implicit Intents

## 💡 Helpful Resources
- [Twitter's Web Intent Documentation](https://developer.twitter.com/en/docs/twitter-for-websites/tweet-button/guides/web-intent)
- [ImageView Documentation](https://developer.android.com/reference/android/widget/ImageView#setImageURI(android.net.Uri))
- [Intents and Intent Filters](https://developer.android.com/guide/components/intents-filters)
- [Common Intents](https://developer.android.com/guide/components/intents-common~~~~)

## 💡 How do I send a Tweet with an Implicit Intent?
To do this, we need two things:
1. We need an Intent with the action `Intent.ACTION_VIEW`
2. We need a valid Twitter url to resolve

`https://twitter.com/intent/tweet?text=%s`

```kotlin
private fun createTweetIntent(): Intent {
    // Create intent using ACTION_VIEW and a normal Twitter url:
    val tweetUrl = String.format(
      "https://twitter.com/intent/tweet?text=%s",
      URLEncoder.encode("I'm learning a lot about #androiddev today!", "UTF-8")
    )
    return Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl))
}
```

## 💡 How to send a Share Intent?
There are many ways to do this; depending on how general or specific your want to be.

You can customize your message for emails, text messages, note apps, etc.

For a general Share Intent that could send a text or an email, we can use something like the following:
```kotlin
private fun sendShareIntent() {
    val intent = Intent(Intent.ACTION_SEND).apply {
      type = "text/plain"
      putExtra(Intent.EXTRA_SUBJECT, "Android Bootcamp w/ Kotlin")
      putExtra(Intent.EXTRA_TEXT, "I'm learning a lot about Android development today!!")
    }
    startActivity(intent)
}
```

## 💡 How to send an Intent to select an image?
There are a lot of different ways to accomplish this.
Try the following
```kotlin
fun selectImage(activity: Activity) {
    val intent = Intent()
    intent.type = "image/*"
    intent.action = Intent.ACTION_GET_CONTENT
    activity.startActivityForResult(Intent.createChooser(intent, "Select Picture"), CHOOSE_PHOTO_REQUEST)
}
```

## 💡 How do I get the selected image URI
If we launch an "image selection" `Intent` using `startActivityForResult()`, then we can respond to the returned result by overriding `onActivityResult()`.

```kotlin
override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    super.onActivityResult(requestCode, resultCode, data)
    if(requestCode != CHOOSE_PHOTO_REQUEST || resultCode == Activity.RESULT_CANCELED) return

    if (resultCode == Activity.RESULT_OK && data != null) {
      noteImageView.setImageURI(data.data)
      return
    }

    // display some kind of error feedback to user if image selection failed
    view?.let { currentView ->
      Snackbar.make(currentView, getString(R.string.photo_selection_error), Snackbar.LENGTH_SHORT).show()
    }
}
```

## 💡 How to show a local image URI in an ImageView?
`ImageView` has a method specifically for this.

Locate the URI to an image in the local filesystem, and call `ImageView.setImageURI()`
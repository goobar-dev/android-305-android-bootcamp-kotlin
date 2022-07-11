# 🖥 Lab 18: Integrating a Web App into Your Application
Integrate the Twitter PWA into the app as an alternative to using Implicit Intents.

## Objectives
1. Review existing use of `Intent` to launch Twitter
    - Notice that this takes user to another app to carry out the action
2. Load Twitter's PWA using a `WebView`
    1. Add a `WebView` to `activity_twitter.xml`
    2. Enable Javascript on the `WebView`
    3. Call `WebView.loadUrl()` with `"https://mobile.twitter.com"`
3. Load Twitter's PWA directly into the "Composer"
    1. Replace the loading of `"https://mobile.twitter.com"` with `"https://mobile.twitter.com/intent/tweet?hashtags=$csvFormattedHashtags"`
4. Display a native `ProgressBar` when PWA is loading
    1. Create a `ProgressChromeClient` class that extends `WebChromeClient`
    2. Update the constructor to take a single `ProgressBar` property
    3. Within `onProgressChanged()` show the `ProgressBar` any time the current progress is between 1-99
    4. Set an instance of `ProgressChromeClient` as the `webChromeClient` on the `WebView`
    5. Re-launch your `TwitterActivity` to observe your native `ProgressBar` being updated in response to loading of the PWA
5. Display yor own simple web app that bridges between Javascript and Kotlin
    1. Create a class `SimpleWebAppInterface` that takes an `Activity` as a constructor property
    2. Add two methods to the class `fun showToast(toast: String)` and `fun navigateBack()`
    3. Annotate these methods with `@JavascriptInterface`
    4. Copy over the `WebView.loadSimpleWebApp()` function from the hints
    5. Invoke `loadSimpleWebApp()` from `TwitterActivity.onCreate()`
    6. Re-launch your `TwitterActivity` to observe that actions taken from the Javascript are responded to Natively

## Challenges
1. Add additional styling/actions to the HTML defined in `loadSimpleWebApp()` and respond natively
2. Add a `FloatingActionButton` that is drawn over your custom HTML/Javascript and that launches the Twitter PWA when clicked
    1. This demonstrates the mixing of native controls with web content for a hybrid approach

# 🖥 Lab 18 Hints: Integrating a Web App into Your Application

## 💡 Helpful Resources
- [Android WebView Guide](https://developer.android.com/guide/webapps/webview)
- [Enabling Javascript in a WebView](https://developer.android.com/guide/webapps/webview#EnablingJavaScript)
- [Connecting Javascript to Kotlin](https://developer.android.com/guide/webapps/webview#BindingJavaScript)
- [Android WebView Docs](https://developer.android.com/reference/android/webkit/WebView)
- [How we built Twitter Lite](https://blog.twitter.com/engineering/en_us/topics/open-source/2017/how-we-built-twitter-lite)

## 💡 How to load Twitter's PWA into a WebView?
```kotlin
fun WebView.loadTwitter() {
  loadUrl("https://mobile.twitter.com")
}
```

## 💡 How to load Twitter's PWA into a WebView directly to the Composer
```kotlin
/**
 * Will load Twitter's PWA "Twitter Lite" and open up to the composer with a set of pre-populated
 * hashtags
 */
fun WebView.loadTwitterComposer(vararg hashtags: String) {
  val formattedHashtags = hashtags.toList().reduceRightIndexed { index, s, acc ->
    val separator = if(index == hashtags.size -1) "" else ","
    acc + separator + s
  }
  loadUrl("https://mobile.twitter.com/intent/tweet?hashtags=$formattedHashtags")
}
```

## 💡 Where can I find some simple HTML/Javascript to load into my WebView?
Look no further!

Ensure that you've added the `SimpleWebAppInterface` class to yor project.
Then, add the `WebView.loadSimpleWebApp()` extension function and invoke it after setting up your `WebView`   in `onCreate()`

The created instance of `SimpleWebAppInterface` will be used to connect to click handlers defined in the Javascript

```kotlin
/**
 * Basic example of a Kotlin -> Javascript bridging implementation
 */
class SimpleWebAppInterface(private val activity: Activity) {

    @JavascriptInterface
    fun showToast(toast: String) {
        Toast.makeText(activity, toast, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun navigateBack() {
        activity.finish()
    }
}

/**
 * Will load some basic html/javascript with a bridging class to communicate back to the Activity
 */
fun WebView.loadSimpleWebApp(activity: Activity) {
  addJavascriptInterface(SimpleWebAppInterface(activity), "SimpleWebAppInterface")

  val html = """
      <!DOCTYPE html>
      <html>
      <head>
      <style>
      body {background-color: powderblue;}
      h1   {color: blue;}
      p    {color: red;}
      </style>
      </head>
      <body>
      
      <input type="button" value="Say hello" onClick="showAndroidToast('Hello Android!')" />
      <input type="button" value="Finish" onClick="navigateBack()" />
      <h1>Simple Web App</h1>
      <p>Connect HTML/Javascript elements to our mobile app</p>
      </body>
      
      <script type="text/javascript">
          function showAndroidToast(toast) {
              SimpleWebAppInterface.showToast(toast);
          }
          
          function navigateBack() {
              SimpleWebAppInterface.navigateBack();
          }
      </script>
      
      </html>
    """.trimIndent()
  val encodedHtml = Base64.encodeToString(html.toByteArray(), Base64.NO_PADDING)
  loadData(encodedHtml, "text/html", "base64")
}
```
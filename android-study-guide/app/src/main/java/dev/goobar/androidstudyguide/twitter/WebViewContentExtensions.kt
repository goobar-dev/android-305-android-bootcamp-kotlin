package dev.goobar.androidstudyguide.twitter

import android.app.Activity
import android.util.Base64
import android.webkit.WebView

/**
 * Will load Twitter's PWA "Twitter Lite"
 */
fun WebView.loadTwitter() {
  loadUrl("https://mobile.twitter.com")
}

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
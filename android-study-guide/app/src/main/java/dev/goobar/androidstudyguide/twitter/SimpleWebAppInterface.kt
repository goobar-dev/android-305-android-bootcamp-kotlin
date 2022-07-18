package dev.goobar.androidstudyguide.twitter

import android.app.Activity
import android.webkit.JavascriptInterface
import android.widget.Toast

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

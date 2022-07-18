package dev.goobar.androidstudyguide.twitter

import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.ProgressBar

/**
 * Reports on progress of the current loading page and shows a [ProgressBar] when loading
 */
class ProgressChromeClient(private val progressBar: ProgressBar) : WebChromeClient() {

  override fun onProgressChanged(view: WebView?, newProgress: Int) {
    super.onProgressChanged(view, newProgress)
    progressBar.visibility = if (newProgress in 1..99) {
      View.VISIBLE
    } else {
      View.GONE
    }
  }

}
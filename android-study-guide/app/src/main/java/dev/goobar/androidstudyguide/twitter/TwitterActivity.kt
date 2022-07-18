package dev.goobar.androidstudyguide.twitter

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.goobar.androidstudyguide.R.layout
import java.net.URLEncoder

class TwitterActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_twitter)

    startActivity(createTweetIntent())
  }

  private fun createTweetIntent(): Intent {
    // Create intent using ACTION_VIEW and a normal Twitter url:
    val tweetUrl = String.format(
      "https://twitter.com/intent/tweet?text=%s",
      URLEncoder.encode("I'm learning a lot about #androiddev today!", "UTF-8")
    )
    return Intent(Intent.ACTION_VIEW, Uri.parse(tweetUrl))
  }
}
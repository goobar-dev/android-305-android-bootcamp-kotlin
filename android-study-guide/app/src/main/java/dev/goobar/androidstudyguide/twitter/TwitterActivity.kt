package dev.goobar.androidstudyguide.twitter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.goobar.androidstudyguide.R.layout

class TwitterActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(layout.activity_twitter)
  }
}
package dev.goobar.composelocations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dev.goobar.composelocations.ui.theme.ComposeLocationsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeLocationsTheme {
        ComposeLocationsNavigation()
      }
    }
  }
}
package dev.goobar.androidstudyguide

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.goobar.androidstudyguide.R.id
import dev.goobar.androidstudyguide.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.toolbar.setOnMenuItemClickListener { item ->
      if (item.itemId == R.id.shareOnTwitter) {
        startActivity(Intent(this@MainActivity, TwitterActivity::class.java))
        true
      }
      false
    }
    //setSupportActionBar(binding.toolbar)
    val appBarConfiguration = AppBarConfiguration(setOf(id.myNotesFragment, id.studyGuideFragment))

    val navController = binding.fragmentContainer.getFragment<NavHostFragment>().navController
    binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    binding.bottomNavigation.setupWithNavController(navController)

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
      val showBottomNav = arguments?.getBoolean("ShowBottomNav", false) == true
      binding.bottomNavigation.visibility = if(showBottomNav) View.VISIBLE else View.GONE
    }
  }

  override fun onBackPressed() {
    if(findNavController(R.id.fragmentContainer).popBackStack()) return
    super.onBackPressed()
  }
}
package dev.goobar.composelocations

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.goobar.composelocations.main.MainScreen
import dev.goobar.composelocations.map.MapScreen
import dev.goobar.composelocations.navigation.Destinations

@Composable
fun ComposeLocationsNavigation() {
  val navController = rememberNavController()

  NavHost(navController = navController, startDestination = Destinations.Main.route) {
    composable(Destinations.Main.route) {
      MainScreen() { location ->
        navController.navigate(Destinations.Map.createRoute(location))
      }
    }
    composable(Destinations.Map.route) {
      val location = Destinations.Map.parseArgs(it.arguments)
      MapScreen(location) {
        navController.popBackStack()
      }
    }
  }
}
package dev.goobar.composelocations.navigation

import android.os.Bundle
import dev.goobar.composelocations.data.Location
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.Base64

sealed class Destinations(val route: String) {

  object Main: Destinations("/main")
  object Map: Destinations("/map/{location}") {
    fun createRoute(location: Location): String {
      val locationJson = Json.encodeToString(Location.serializer(), location)
      val encodedJson = URLEncoder.encode(locationJson)
      return "/map/$encodedJson"
    }
    fun parseArgs(args: Bundle?): Location {
      val encodedJson = args?.getString("location") ?: throw IllegalStateException()
      val requestJson = URLDecoder.decode(encodedJson)
      val location: Location = Json.decodeFromString(requestJson)
      return location
    }
  }
}
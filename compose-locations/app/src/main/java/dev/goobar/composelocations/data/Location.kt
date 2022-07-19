package dev.goobar.composelocations.data

import kotlinx.serialization.Serializable

@Serializable
data class Location(
  val label: String,
  val lat: Double,
  val lon: Double
)

val LOCATIONS = listOf(
  Location(
    label = "Googleplex",
    lat = 37.42,
    lon = -122.08
  ),
  Location(
    label = "Intuit San Diego",
    lat = 32.959,
    lon = -117.158
  ),
  Location(
    label = "Guru Donuts, Boise Idaho",
    lat = 43.617,
    lon = -116.20
  )
)

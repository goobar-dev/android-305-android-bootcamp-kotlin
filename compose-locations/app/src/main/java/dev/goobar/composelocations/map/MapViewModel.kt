package dev.goobar.composelocations.map

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import dev.goobar.composelocations.data.Location
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class MapViewModelFactory(
  private val context: Context,
  private val location: Location
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return MapViewModel(
      LocationServices.getFusedLocationProviderClient(context),
      location
    ) as T
  }
}

class MapViewModel(
  private val locationClient: FusedLocationProviderClient,
  location: Location
): ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(
    UiState(
      title = location.label,
      location = location,
      markerLabel = location.label,
      zoom = 10f
    )
  )

  @SuppressLint("MissingPermission")
  fun onGpsClick() {
    viewModelScope.launch(Dispatchers.IO) {
      locationClient.requestLocationUpdates(LocationRequest(), Executors.newSingleThreadExecutor(), object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
          val currentLocation = result.lastLocation
          if(currentLocation == null) {
            println("location was null")
            return
          }

          state.update {
            UiState(
              title = "Current location",
              location = Location("Current location", currentLocation.latitude, currentLocation.longitude),
              markerLabel = "Current location",
              zoom = 15f
            )
          }
        }
      })

      locationClient.lastLocation.addOnSuccessListener { currentLocation ->
        if(currentLocation == null) {
          println("location was null")
          return@addOnSuccessListener
        }

        state.update {
          UiState(
            title = "Current location",
            location = Location("Current location", currentLocation.latitude, currentLocation.longitude),
            markerLabel = "Current location",
            zoom = 15f
          )
        }
      }
    }
  }

  data class UiState(
    val title: String,
    val location: Location,
    val markerLabel: String,
    val zoom: Float
  )
}
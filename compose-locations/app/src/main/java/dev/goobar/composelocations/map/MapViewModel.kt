package dev.goobar.composelocations.map

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dev.goobar.composelocations.data.Location
import kotlinx.coroutines.flow.MutableStateFlow

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
    // todo
  }

  data class UiState(
    val title: String,
    val location: Location,
    val markerLabel: String,
    val zoom: Float
  )
}
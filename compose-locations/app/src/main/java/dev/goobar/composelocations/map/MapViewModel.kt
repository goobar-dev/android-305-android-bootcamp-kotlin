package dev.goobar.composelocations.map

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.goobar.composelocations.data.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapViewModelFactory(private val location: Location) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return MapViewModel(location) as T
  }
}

class MapViewModel(location: Location): ViewModel() {

  val state: StateFlow<UiState> = MutableStateFlow(
    UiState(
      title = location.label,
      location = location,
      markerLabel = location.label,
      zoom = 10f
    )
  )

  data class UiState(
    val title: String,
    val location: Location,
    val markerLabel: String,
    val zoom: Float
  )
}
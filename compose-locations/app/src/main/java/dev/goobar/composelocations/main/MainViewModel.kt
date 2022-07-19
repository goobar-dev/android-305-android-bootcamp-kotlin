package dev.goobar.composelocations.main

import androidx.lifecycle.ViewModel
import dev.goobar.composelocations.data.LOCATIONS
import dev.goobar.composelocations.data.Location
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

  val state: StateFlow<UiState> = MutableStateFlow(
    UiState(LOCATIONS)
  )

  data class UiState(val locations: List<Location>)
}
package dev.goobar.composelocations.map

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.goobar.composelocations.R.drawable
import dev.goobar.composelocations.data.Location

@Composable
fun MapScreen(
  location: Location,
  viewModel: MapViewModel = viewModel(
    factory = MapViewModelFactory(location)
  ),
  onBackClick: () -> Unit
) {
  val state by viewModel.state.collectAsState()

  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = state.title, style = MaterialTheme.typography.titleMedium)
        },
        navigationIcon = {
          IconButton(
            onClick = onBackClick,
            content = { Icon(painterResource(drawable.ic_baseline_arrow_back_24), "Back button") }
          )
        }
      )
    },
    content = {
      MapContent(state, modifier = Modifier.padding(it))
    }
  )
}

@Composable
private fun MapContent(state: MapViewModel.UiState, modifier: Modifier) {
  val selectedLocation by derivedStateOf { LatLng(state.location.lat, state.location.lon) }
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(selectedLocation, state.zoom)
  }
  GoogleMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
  ) {
    Marker(
      state = MarkerState(position = selectedLocation),
      title = state.markerLabel
    )
  }
}
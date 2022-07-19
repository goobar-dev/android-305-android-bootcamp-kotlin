package dev.goobar.composelocations.map

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.goobar.composelocations.R
import dev.goobar.composelocations.R.drawable
import dev.goobar.composelocations.data.Location

@Composable
fun MapScreen(
  location: Location,
  viewModel: MapViewModel = viewModel(
    factory = MapViewModelFactory(LocalContext.current, location)
  ),
  onBackClick: () -> Unit,
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
    floatingActionButton = { GPSButton() {
      viewModel.onGpsClick()
    } },
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
  val coroutineScope = rememberCoroutineScope()
  LaunchedEffect(selectedLocation) {
    cameraPositionState.animate(CameraUpdateFactory.newCameraPosition(CameraPosition.fromLatLngZoom(selectedLocation, state.zoom)))
  }

  GoogleMap(
    modifier = Modifier.fillMaxSize(),
    uiSettings = MapUiSettings(zoomControlsEnabled = false),
    cameraPositionState = cameraPositionState
  ) {
    Marker(
      state = MarkerState(position = selectedLocation),
      title = state.markerLabel
    )
  }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun GPSButton(onGPSClick: () -> Unit) {

  val locationPermissionState = rememberPermissionState(
    android.Manifest.permission.ACCESS_FINE_LOCATION
  )

  when (locationPermissionState.status) {
    // If the permission is granted, then show location fab with the feature enabled
    PermissionStatus.Granted -> {
      SmallFloatingActionButton(onClick = onGPSClick) {
        Icon(painter = painterResource(id = R.drawable.ic_baseline_location_on_24), contentDescription = "Current location")
      }
    }
    is PermissionStatus.Denied -> {
      Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier.background(
          brush = Brush.verticalGradient(
            colors = listOf(
              Color.White.copy(alpha = .1f),
              Color.Gray.copy(alpha = .5f),
            )
          )
        )
      ) {
        val rationale = when(val shouldShowRationale = locationPermissionState.status.shouldShowRationale) {
          true -> "Without location permission, you will not be able to access your current location"
          false -> "Location permission is required to get your location"
        }
        SmallFloatingActionButton(onClick = { locationPermissionState.launchPermissionRequest() }) {
          Icon(painter = painterResource(id = R.drawable.ic_baseline_location_on_24), contentDescription = "Current location")
        }
        Text(rationale)
      }
    }
  }
}
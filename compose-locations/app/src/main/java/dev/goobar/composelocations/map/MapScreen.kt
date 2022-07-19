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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dev.goobar.composelocations.R.drawable
import dev.goobar.composelocations.data.Location

@Composable
fun MapScreen(location: Location, onBackClick: () -> Unit) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = location.label, style = MaterialTheme.typography.titleMedium)
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
      MapContent(location, modifier = Modifier.padding(it))
    }
  )
}

@Composable
private fun MapContent(location: Location, modifier: Modifier) {
  val selectedLocation by derivedStateOf { LatLng(location.lat, location.lon) }
  val cameraPositionState = rememberCameraPositionState {
    position = CameraPosition.fromLatLngZoom(selectedLocation, 10f)
  }
  GoogleMap(
    modifier = Modifier.fillMaxSize(),
    cameraPositionState = cameraPositionState
  ) {
    Marker(
      state = MarkerState(position = selectedLocation),
      title = location.label
    )
  }
}
package dev.goobar.composelocations.main

import androidx.compose.foundation.layout.Arrangement.Absolute
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import dev.goobar.composelocations.data.LOCATIONS
import dev.goobar.composelocations.data.Location

@Composable
fun MainScreen(
  viewModel: MainViewModel = viewModel(),
  onClick: (Location) -> Unit
) {
  val state by viewModel.state.collectAsState()

  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = "Compose Locations", style = MaterialTheme.typography.titleMedium)
        },
      )
    },
    content = {
      MainListContent(
        locations = state.locations,
        modifier = Modifier.padding(it),
        onClick = onClick
      )
    }
  )
}

@Composable
private fun MainListContent(
  locations: List<Location>,
  modifier: Modifier,
  onClick: (Location) -> Unit
) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(20.dp),
    verticalArrangement = Absolute.spacedBy(20.dp)
  ) {
    items(locations) { location ->
      LocationCard(location, onClick)
    }
  }
}

@Composable
private fun LocationCard(location: Location, onClick: (Location) -> Unit) {
  ElevatedCard(
    modifier = Modifier.fillMaxWidth(1f),
    onClick = {
      onClick(location)
    }
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Text(location.label, style = MaterialTheme.typography.titleMedium)
      Text("${location.lat}, ${location.lon}", style = MaterialTheme.typography.bodyMedium)
    }
  }
}
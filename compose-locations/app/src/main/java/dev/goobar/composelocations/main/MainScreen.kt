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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.goobar.composelocations.data.LOCATIONS
import dev.goobar.composelocations.data.Location

@Composable
fun MainScreen(onClick: (Location) -> Unit) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = "Compose Locations", style = MaterialTheme.typography.titleMedium)
        },
      )
    },
    content = {
      MainListContent(Modifier.padding(it), onClick)
    }
  )
}

@Composable
private fun MainListContent(modifier: Modifier, onClick: (Location) -> Unit) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(20.dp),
    verticalArrangement = Absolute.spacedBy(20.dp)
  ) {
    items(LOCATIONS) { location ->
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
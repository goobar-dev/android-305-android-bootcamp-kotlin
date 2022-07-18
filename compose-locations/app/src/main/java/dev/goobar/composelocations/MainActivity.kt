package dev.goobar.composelocations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import dev.goobar.composelocations.ui.theme.ComposeLocationsTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      ComposeLocationsTheme {
        var selectedIndex: Int? by remember { mutableStateOf(null) }

        if (selectedIndex == null) {
          MainScreen() { index ->
            selectedIndex = index
          }
        } else {
          MapScreen(index = selectedIndex!!) {
            selectedIndex = null
          }
        }
      }
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainScreen(onClick: (Int) -> Unit) {
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainListContent(modifier: Modifier, onClick: (Int) -> Unit) {
  LazyColumn(
    modifier = modifier,
    contentPadding = PaddingValues(20.dp),
    verticalArrangement = spacedBy(20.dp)
  ) {
    items(10) { index ->
      LocationCard(index = index, onClick)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationCard(index: Int, onClick: (Int) -> Unit) {
  ElevatedCard(
    modifier = Modifier.fillMaxWidth(1f),
    onClick = {
      onClick(index)
    }
  ) {
    Column(modifier = Modifier.padding(20.dp)) {
      Text("Location $index", style = MaterialTheme.typography.titleMedium)
      Text("A description for Location $index", style = MaterialTheme.typography.bodyMedium)
    }
  }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MapScreen(index: Int, onBackClick: () -> Unit) {
  Scaffold(
    topBar = {
      SmallTopAppBar(
        title = {
          Text(text = "Location $index", style = MaterialTheme.typography.titleMedium)
        },
        navigationIcon = {
          IconButton(
            onClick = onBackClick,
            content = { Icon(painterResource(R.drawable.ic_baseline_arrow_back_24), "Back button") }
          )
        }
      )
    },
    content = {
      Text("Map Goes Here", modifier = Modifier.padding(it).padding(horizontal = 20.dp))
    }
  )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  ComposeLocationsTheme {
    MainScreen() {}
  }
}
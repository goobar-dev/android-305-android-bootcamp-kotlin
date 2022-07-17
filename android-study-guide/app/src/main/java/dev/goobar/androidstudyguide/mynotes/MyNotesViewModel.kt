package dev.goobar.androidstudyguide.mynotes

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class MyNotesViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState("My Notes Fragment", emptyList()))

  data class UiState(
    val title: String,
    val notes: List<String>
  )
}
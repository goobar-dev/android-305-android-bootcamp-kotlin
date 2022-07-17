package dev.goobar.androidstudyguide.details

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class NoteDetailsViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(
    UiState(
      title = "My Android notes",
      category = "Tooling",
      content = "Android is a complex subject.  It can take a lot of work to learn."
    )
  )

  data class UiState(
    val title: String,
    val category: String,
    val content: String
  )
}
package dev.goobar.androidstudyguide.mynotes

import androidx.lifecycle.ViewModel
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.data.SAMPLE_NOTES
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class MyNotesViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState("My Notes Fragment", emptyList()))

  init {
    state.update { currentState -> currentState.copy(notes = SAMPLE_NOTES) }
  }

  data class UiState(
    val title: String,
    val notes: List<Note>
  )
}
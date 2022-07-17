package dev.goobar.androidstudyguide.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.goobar.androidstudyguide.data.Note
import kotlinx.coroutines.flow.MutableStateFlow

class NoteDetailsViewModelFactory(
  private val note: Note
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return NoteDetailsViewModel(note) as T
  }
}

class NoteDetailsViewModel(private val note: Note) : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(
    UiState(
      title = note.title,
      category = note.category,
      content = note.content
    )
  )

  data class UiState(
    val title: String,
    val category: String,
    val content: String
  )
}
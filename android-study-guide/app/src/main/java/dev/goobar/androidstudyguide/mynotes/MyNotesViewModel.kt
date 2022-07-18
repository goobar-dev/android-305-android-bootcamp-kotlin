package dev.goobar.androidstudyguide.mynotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.create.CreateNoteViewModel
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.data.SAMPLE_NOTES
import dev.goobar.androidstudyguide.datastore.DefaultCategoryRepository
import dev.goobar.androidstudyguide.db.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class MyNotesViewModelFactory(
  private val noteDao: NoteDao
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return MyNotesViewModel(noteDao) as T
  }
}

class MyNotesViewModel(private val noteDao: NoteDao) : ViewModel() {

  val state: StateFlow<UiState> = noteDao
    .getAll()
    .map { UiState(it) }
    .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), UiState()
  )

  data class UiState(val notes: List<Note> = emptyList())
}
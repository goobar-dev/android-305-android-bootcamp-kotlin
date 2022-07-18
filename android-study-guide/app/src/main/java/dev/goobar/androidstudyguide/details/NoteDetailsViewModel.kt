package dev.goobar.androidstudyguide.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.db.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDetailsViewModelFactory(
  private val id: Int,
  private val noteDao: NoteDao,
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return NoteDetailsViewModel(id, noteDao) as T
  }
}

class NoteDetailsViewModel(
  private val id: Int,
  private val noteDao: NoteDao,
) : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState("", "", ""))

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val selectedNote = noteDao.get(id)

      state.update { UiState(selectedNote.title, selectedNote.category, selectedNote.content) }
    }
  }

  data class UiState(
    val title: String,
    val category: String,
    val content: String
  )
}
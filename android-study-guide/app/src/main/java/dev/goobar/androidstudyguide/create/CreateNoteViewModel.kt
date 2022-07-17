package dev.goobar.androidstudyguide.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kotlinx.coroutines.flow.MutableStateFlow

private val CATEGORIES = listOf("Tooling", "Kotlin", "UI", "Navigation", "Misc")

class CreateNoteViewModelFactory() : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return CreateNoteViewModel() as T
  }
}

class CreateNoteViewModel() : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(CATEGORIES))

  data class UiState(
    val categories: List<String>
  )
}
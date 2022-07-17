package dev.goobar.androidstudyguide.create

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

private val CATEGORIES = listOf("Tooling", "Kotlin", "UI", "Navigation", "Misc")

class CreateNoteViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(CATEGORIES))

  data class UiState(
    val categories: List<String>
  )
}
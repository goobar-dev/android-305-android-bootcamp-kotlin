package dev.goobar.androidstudyguide.studyguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.data.Note
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyGuideViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(title = "Study Guide", notes = emptyList()))

  init {
    viewModelScope.launch {
      delay(3000)
      state.update { currentValue -> currentValue.copy(title = "Study Guide Fragment") }
    }
  }

  data class UiState(
    val title: String,
    val notes: List<String>
  )
}
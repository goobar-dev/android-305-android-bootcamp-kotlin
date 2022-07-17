package dev.goobar.androidstudyguide.studyguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.data.Topic
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyGuideViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(topics = emptyList()))

  init {
    viewModelScope.launch {
      delay(3000)
      state.update { currentValue ->
        UiState(
          listOf(
            Topic("Sample Topic 1", listOf("Android"), "A Sample topic"),
            Topic("Sample Topic 2", listOf("Kotlin"),"A Sample topic"),
            Topic("Sample Topic 3", listOf("Cloud"),"A Sample topic"),
          )
        )
      }
    }
  }

  data class UiState(
    val topics: List<Topic>
  )
}
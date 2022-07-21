package dev.goobar.androidstudyguide.studyguide

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.data.Topic
import dev.goobar.androidstudyguide.network.studyGuideService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StudyGuideViewModel : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(topics = emptyList()))

  init {
    viewModelScope.launch(Dispatchers.IO) {
      val topics = studyGuideService.getTopics()
      state.update { currentValue -> UiState(false, topics) }
    }
  }

  data class UiState(
    val isLoading: Boolean = true,
    val topics: List<Topic> = emptyList()
  )
}
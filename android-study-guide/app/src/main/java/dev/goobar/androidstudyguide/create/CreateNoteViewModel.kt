package dev.goobar.androidstudyguide.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dev.goobar.androidstudyguide.data.Note
import dev.goobar.androidstudyguide.datastore.DefaultCategoryRepository
import dev.goobar.androidstudyguide.db.NoteDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

private val CATEGORIES = listOf("Tooling", "Kotlin", "UI", "Navigation", "Misc")

class CreateNoteViewModelFactory(
  private val categoryRepository: DefaultCategoryRepository,
  private val noteDao: NoteDao
) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return CreateNoteViewModel(categoryRepository, noteDao) as T
  }
}

class CreateNoteViewModel(
  private val categoryRepository: DefaultCategoryRepository,
  private val noteDao: NoteDao
  ) : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(CATEGORIES))

  fun save(title: String, categoryIndex: Int, content: String) {
    viewModelScope.launch { noteDao.save(Note(title, CATEGORIES[categoryIndex], content)) }
  }

  suspend fun saveSelectedCategory(selectedIndex: Int) = categoryRepository.updateDefaultCategory(CATEGORIES[selectedIndex])

  fun indexForCategory(category: String): Int = CATEGORIES.indexOf(category)

  data class UiState(
    val categories: List<String>
  )
}
package dev.goobar.androidstudyguide.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.goobar.androidstudyguide.datastore.DefaultCategoryRepository
import kotlinx.coroutines.flow.MutableStateFlow

private val CATEGORIES = listOf("Tooling", "Kotlin", "UI", "Navigation", "Misc")

class CreateNoteViewModelFactory(private val categoryRepository: DefaultCategoryRepository) : ViewModelProvider.Factory {
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    return CreateNoteViewModel(categoryRepository) as T
  }
}

class CreateNoteViewModel(private val categoryRepository: DefaultCategoryRepository) : ViewModel() {

  val state: MutableStateFlow<UiState> = MutableStateFlow(UiState(CATEGORIES))

  suspend fun saveSelectedCategory(selectedIndex: Int) = categoryRepository.updateDefaultCategory(CATEGORIES[selectedIndex])

  fun indexForCategory(category: String): Int = CATEGORIES.indexOf(category)

  data class UiState(
    val categories: List<String>
  )
}
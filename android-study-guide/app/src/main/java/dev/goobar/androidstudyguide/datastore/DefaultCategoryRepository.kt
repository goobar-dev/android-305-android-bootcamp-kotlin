package dev.goobar.androidstudyguide.datastore

import android.content.Context
import dev.goobar.androidstudyguide.protos.DefaultCategory
import kotlinx.coroutines.flow.Flow

interface DefaultCategoryRepository {
  val defaultCategory: Flow<DefaultCategory>
  suspend fun updateDefaultCategory(category: String)
}

class DataStoreCategoryRepository(private val context: () -> Context) : DefaultCategoryRepository {

  override val defaultCategory: Flow<DefaultCategory>
    get() = context().defaultCategoryDataStore.data

  override suspend fun updateDefaultCategory(category: String) {
    context().defaultCategoryDataStore.updateData { defaultCategory ->
      defaultCategory.toBuilder().setCategory(category).build()
    }
  }

}
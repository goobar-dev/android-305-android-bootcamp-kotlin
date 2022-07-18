package dev.goobar.androidstudyguide.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import dev.goobar.androidstudyguide.protos.DefaultCategory
import java.io.InputStream
import java.io.OutputStream

object DefaultCategorySerializer : Serializer<DefaultCategory> {
  override val defaultValue: DefaultCategory = DefaultCategory.getDefaultInstance()

  override suspend fun readFrom(input: InputStream): DefaultCategory {
    return DefaultCategory.parseFrom(input)
  }

  override suspend fun writeTo(t: DefaultCategory, output: OutputStream) = t.writeTo(output)
}

val Context.defaultCategoryDataStore: DataStore<DefaultCategory> by dataStore(
  fileName = "category.pb",
  serializer = DefaultCategorySerializer
)
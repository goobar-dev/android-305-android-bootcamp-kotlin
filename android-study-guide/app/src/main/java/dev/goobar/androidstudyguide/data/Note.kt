package dev.goobar.androidstudyguide.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
data class Note(
  val title: String,
  val category: String,
  val content: String,
) {
  @PrimaryKey(autoGenerate = true)
  var id: Int = 0
}

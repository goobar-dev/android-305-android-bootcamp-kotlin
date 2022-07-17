package dev.goobar.androidstudyguide.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
  val title: String,
  val category: String,
  val content: String,
): Parcelable

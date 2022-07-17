package dev.goobar.androidstudyguide.data

data class Topic(
  val title: String,
  val categories: List<String>,
  val content: String
)
package dev.goobar.lab3

import dev.goobar.lab3.SupportedAndroidLanguageException.EmptyNameException
import dev.goobar.lab3.SupportedAndroidLanguageException.InvalidNameException

sealed class SupportedAndroidLanguageException : Throwable() {
  object EmptyNameException : SupportedAndroidLanguageException()
  data class InvalidNameException(val name: String) : SupportedAndroidLanguageException()
}

private val supportedLanguages = listOf ("c++", "kotlin", "java")
fun isSupportedAndroidLangauge(language: String): Boolean {
  return when {
    language.isBlank() -> {
      throw EmptyNameException
    }
    supportedLanguages.contains(language.lowercase()) -> {
      return true
    }
    else -> {
      throw InvalidNameException(language)
    }
  }
}
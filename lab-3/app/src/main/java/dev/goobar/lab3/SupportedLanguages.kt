package dev.goobar.lab3

import dev.goobar.lab3.SupportedAndroidLanguageException.EmptyNameException
import dev.goobar.lab3.SupportedAndroidLanguageException.InvalidNameException

sealed class SupportedAndroidLanguageException : Throwable() {
  object EmptyNameException : SupportedAndroidLanguageException()
  data class InvalidNameException(val name: String) : SupportedAndroidLanguageException()
}

private val supportedLanguages = listOf ("c++", "kotlin", "java")
fun isSupportedAndroidLangauge(language: String): Boolean {
  TODO("Use a when expression to evaluate the passed language")
}
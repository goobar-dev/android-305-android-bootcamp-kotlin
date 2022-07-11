package dev.goobar.lab3

fun getShortNames(names: List<String?>): List<String> {
  return names
    .filterNotNull()
    .filter { it.isNotBlank() }
    .filter { it.length <= 4 }
}
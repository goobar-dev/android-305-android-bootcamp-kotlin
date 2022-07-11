package dev.goobar.lab3

import org.junit.Test

class CollectionsTests {

  @Test
  fun `handles empty input`() {
    assert(getShortNames(listOf()).isEmpty())
  }

  @Test
  fun `handles null inputs`() {
    assert(getShortNames(listOf(null, null, null)).isEmpty())
  }

  @Test
  fun `handles empty names`() {
    assert(getShortNames(listOf("", " ", "   ")).isEmpty())
  }

  @Test
  fun `returns the expected names`() {
    val names = listOf("John", "Jane", "", null, " ", "Peter", "Kotlin", "Steve")

    val returnedNames = getShortNames(names)

    assert(returnedNames.size == 2)
    assert(returnedNames.containsAll(listOf("John", "Jane")))
  }
}
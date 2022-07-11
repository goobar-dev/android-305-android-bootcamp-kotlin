package dev.goobar.lab3

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class OOPTests {
  private val standardOut: PrintStream = System.out
  private val outputStreamCaptor: ByteArrayOutputStream = ByteArrayOutputStream()

  @Before
  fun setUp() {
    System.setOut(PrintStream(outputStreamCaptor))
  }

  @Test
  fun `verify Logger interface`() {
    val customLogger = object : Logger {
      override fun log(msg: String) {
        println(msg)
      }
    }

    customLogger.log("Test some log output")

    Assert.assertEquals(
      "Test some log output",
      outputStreamCaptor.toString().trim()
    )
  }

  @Test
  fun `verify BasicLogger behavior`() {
    BasicLogger.log("Test some log output")

    Assert.assertEquals(
      "Test some log output",
      outputStreamCaptor.toString().trim()
    )
  }

  @Test
  fun `verify FancyLogger behavior`() {
    val fancyLogger = FancyLogger("<Test>")

    fancyLogger.log("Test some log output")

    Assert.assertEquals(
      "<Test>: Test some log output",
      outputStreamCaptor.toString().trim()
    )
  }

  @Test
  fun `verify BasicLogger extension`() {
    val logMessages = listOf(
      "Test some log output",
      "Test more log output"
    )
    BasicLogger.log(logMessages)

    Assert.assertEquals(
      """
      Test some log output
      Test more log output
      """.trimIndent(),
      outputStreamCaptor.toString().trim()
    )
  }
}
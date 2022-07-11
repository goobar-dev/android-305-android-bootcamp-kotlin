package dev.goobar.lab3

interface Logger {
  fun log(msg: String)
}

object BasicLogger : Logger {
  override fun log(msg: String) {
    TODO()
  }
}

class FancyLogger(tag: String) : Logger {

  private val logTag: String = TODO("Assing from passed argument")

  override fun log(msg: String) {
    TODO("Log the passed message with prepended with the logTag")
  }
}

fun BasicLogger.log(msgs: List<String>) {
  TODO("log each passed string")
}
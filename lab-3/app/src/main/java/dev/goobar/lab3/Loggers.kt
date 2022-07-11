package dev.goobar.lab3

interface Logger {
  fun log(msg: String)
}

object BasicLogger : Logger {
  override fun log(msg: String) {
    println(msg)
  }
}

class FancyLogger(tag: String) : Logger {

  private val logTag = tag

  override fun log(msg: String) {
    println("$logTag: $msg")
  }
}

fun BasicLogger.log(msgs: List<String>) {
  msgs.forEach { BasicLogger.log(it) }
}
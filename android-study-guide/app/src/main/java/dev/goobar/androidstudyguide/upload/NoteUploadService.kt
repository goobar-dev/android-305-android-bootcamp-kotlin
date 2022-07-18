package dev.goobar.androidstudyguide.upload

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Process.THREAD_PRIORITY_BACKGROUND
import android.util.Base64
import android.util.Log
import android.widget.Toast
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import dev.goobar.androidstudyguide.BuildConfig
import dev.goobar.androidstudyguide.data.Note
import org.json.JSONObject

class NoteUploadService : Service() {
  private val TAG = NoteUploadService::class.simpleName

  private var serviceLooper: Looper? = null
  private var serviceHandler: ServiceHandler? = null

  override fun onCreate() {
    // Create a separate thread because the service normally runs in the process's
    // main thread, which we don't want to block.  We also make it
    // background priority so CPU-intensive work will not disrupt our UI.
    HandlerThread("ServiceStartArguments", THREAD_PRIORITY_BACKGROUND).apply {
      start()

      // Get the HandlerThread's Looper and use it for our Handler
      serviceLooper = looper // Looper controls the message loop for a Thread
      serviceHandler = ServiceHandler(looper) // Handler have affinity for Thread in which they were created
    }
  }

  override fun onBind(intent: Intent): IBinder? {
    return null
  }

  override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

    // For each start request, send a message to upload the file
    // pass the extras so the HandlerThread can parse Note content
    serviceHandler?.obtainMessage()?.also { msg ->
      msg.data = intent.extras
      serviceHandler?.sendMessage(msg)
    }

    return START_REDELIVER_INTENT
  }

  private fun uploadFileData(context: Context, filename: String, content: String) {
    Thread.sleep(5000)
    val requestQueue = Volley.newRequestQueue(context)

    val body = JSONObject()
    body.put("message", "Upload from app") // commit message of file upload to GitHub
    body.put("content", content)

    val request =  object : JsonObjectRequest(
      Method.PUT,
      "https://api.github.com/repos/goobar-dev/workshop-sample-data/contents/$filename",
      body,
      {
        Log.d(TAG, "Successfully uploaded note")
        Toast.makeText(context, "Successfully uploaded note", Toast.LENGTH_SHORT).show()
        stopSelf()
      },
      {
        Log.d(TAG, "Error uploading note: ${it.message}")
        stopSelf()
      }
    ) {
      override fun getHeaders(): MutableMap<String, String> {
        return mutableMapOf(
          "Authorization" to "token ${BuildConfig.GITHUB_ANDROID_WORKSHOP_TOKEN}",
          "Accept" to "application/vnd.github.v3+json"
        )
      }
    }

    requestQueue.add(request)
  }

  // Handler that receives messages from the main thread
  private inner class ServiceHandler(looper: Looper) : Handler(looper) {

    override fun handleMessage(msg: Message) {

      val noteFilename = parseNoteFilename(msg.data) ?: return
      val noteContent = parseNoteContent(msg.data) ?: return

      uploadFileData(this@NoteUploadService, noteFilename, noteContent)
    }
  }

  companion object {
    private const val KEY_CONTENT = "content"
    private const val KEY_FILENAME = "filename"

    fun getNoteUploadIntent(context: Context, note: Note): Intent {
      return Intent(context, NoteUploadService::class.java).apply {
        putExtra(KEY_CONTENT, encodeNote(note))
        putExtra(KEY_FILENAME, "uploaded-notes/${note.title}-${System.currentTimeMillis()}.txt")
      }
    }

    fun parseNoteContent(data: Bundle): String? {
      return data.getString(KEY_CONTENT)
    }

    fun parseNoteFilename(data: Bundle): String? {
      return data.getString(KEY_FILENAME)
    }

    private fun encodeNote(note: Note): String {
      val noteContent = note.title + "\n" + note.category + "\n" + note.content
      return Base64.encodeToString(noteContent.toByteArray(), android.util.Base64.DEFAULT)
    }
  }
}
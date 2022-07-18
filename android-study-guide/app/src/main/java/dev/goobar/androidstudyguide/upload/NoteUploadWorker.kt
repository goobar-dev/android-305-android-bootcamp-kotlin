package dev.goobar.androidstudyguide.upload

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dev.goobar.androidstudyguide.BuildConfig
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import dev.goobar.androidstudyguide.data.Note
import org.json.JSONObject

class NoteUploadWorker(appContext: Context, workerParams: WorkerParameters): Worker(appContext, workerParams) {
  override fun doWork(): Result {

    // inputData is a property on [Worker]
    // it provides the input data specified by the work request
    val noteUploadConfig = parseInputData(inputData)

    uploadFileData(applicationContext, noteUploadConfig.filename, noteUploadConfig.content)

    // Indicate whether the work finished successfully with the Result
    return Result.success()
  }

  private fun uploadFileData(context: Context, filename: String, content: String) {
    val requestQueue = Volley.newRequestQueue(context)

    val body = JSONObject().apply {
      put("message", "Upload from app") // commit message of file upload to GitHub
      put("content", content)
    }

    val request = getUploadRequest(
      body = body,
      filename,
      onSuccess = {
        Toast.makeText(context, "Successfully uploaded note", Toast.LENGTH_SHORT).show()
      },
      onError = {
        Toast.makeText(context, "Failed to upload note", Toast.LENGTH_SHORT).show()
      }
    )

    requestQueue.add(request)
  }

  companion object {
    private const val KEY_CONTENT = "key_content"
    private const val KEY_FILENAME = "filename"

    fun buildInputData(note: Note): Data = workDataOf(
      KEY_FILENAME to "uploaded-notes/${note.title}-${System.currentTimeMillis()}.txt",
      KEY_CONTENT to encodeNote(note)
    )

    fun parseInputData(data: Data): NoteUploadConfig {
      return NoteUploadConfig(data.getString(KEY_FILENAME)!!, data.getString(KEY_CONTENT)!!)
    }

    private fun encodeNote(note: Note): String {
      val noteContent = note.title + "\n" + note.category + "\n" + note.content
      return Base64.encodeToString(noteContent.toByteArray(), android.util.Base64.DEFAULT)
    }
  }

  data class NoteUploadConfig(val filename: String, val content: String)
}
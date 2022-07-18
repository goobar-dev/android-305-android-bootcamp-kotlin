package dev.goobar.androidstudyguide.upload

import android.widget.Toast
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import dev.goobar.androidstudyguide.BuildConfig
import org.json.JSONObject

/**
 * Defines the Volley request for uploading the file content to GitHub
 */
fun getUploadRequest(
  body: JSONObject,
  filename: String,
  onSuccess: () -> Unit,
  onError: (VolleyError?) -> Unit
): JsonObjectRequest {
  return object : JsonObjectRequest(
    Method.PUT,
    "https://api.github.com/repos/goobar-dev/android-bootcamp-sample-data/contents/$filename",
    body,
    { onSuccess() },
    { onError(it) }
  ) {
    override fun getHeaders(): MutableMap<String, String> {
      return mutableMapOf(
        "Authorization" to "token ${BuildConfig.GITHUB_ANDROID_WORKSHOP_TOKEN}",
        "Accept" to "application/vnd.github.v3+json"
      )
    }
  }
}
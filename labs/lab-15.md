# 🖥 Lab 15: Implementing a Service for Background Upload
Let's implement a service to upload a `Note` to a public `GitHub` repo

## Objectives
1. Add an "Upload" button to `NoteDetailFragment`
    1. When clicked, this button will eventually start our Service

2. Create a new package `upload`

2. Within `upload`, create a `NoteUploadService` that extends `Service` and register it in `AndroidManfiest.xml`

3. Create a companion object in `NoteUploadService`

5. Create a `getNoteUploadIntent(context: Context, note: Note): Intent` method
    1. This should return an `Intent` for starting `NoteUploadService`
    2. This should add an extra for Base64 encoded "content" coming from the passed `Note`
    3. This should add an extra for the filename to store the note as

6. Using `getNoteUploadIntent()`, start the `Service` from `NoteDetailFragment` when the upload button is clicked

7. In `NoteUploadService.onStartCommand()` parse the filename, and content `String`s from the `Intent`

8. Build a `JsonObjectRequest` with Volley to create a new file, in GitHub, containing the contents of your `Note`
    1. Should use a `PUT`
    2. Url should be a url to some public repository that you own and include the desired filename as the final PATH param
    3. The `jsonRequest` body must include `"message"` and `"content"` values
    4. Must include an `Authorization` header to authenticate the request
    5. Add a new `BuildConfig` field named `GITHUB_ANDROID_WORKSHOP_TOKEN` that useas a PAT from GitHub to authenticate the request

9. Move request processing off the main thread
    1. Setup a `HandlerThread` in `NoteUploadService.onCreate()` to manage requests off the main `Thread`
    2. Save a `Looper` reference
    3. Create a `ServiceHandler` class to handle `Looper` `Messages` and upload file data
    4. Create an instance of `ServiceHandler` within your `HandlerThread` on onCreate()
    5. In `onStartCommand()` process incoming `Messages` and send them to your `ServiceHandler` to trigger upload

## Challenges

### Notify user that upload was successful
Add some form of user feedback to indicate that the upload was successful.  
This could be as simple as a `Toast` or `Snackbar`.

### Support note version updates
1. Add a unique id to each filename so that you can upload new copies of a `Note`
2. Use the GitHub api to acquire the SHA for an existing file so you can upload new versions of a file using the same filename

### Let user know an upload is in progress
1. Convert service to a Foreground `Service`
2. Display a notification while upload is in process

# 🖥 Lab 15 Hints: Implementing a Service for Background Upload

## 💡 Helpful Resources
- [Services Overview](https://developer.android.com/guide/components/services)
- [GitHub Contents Api](https://docs.github.com/en/rest/reference/repos#contents)
- [Create GitHub Personal Access Token](https://docs.github.com/en/github/authenticating-to-github/keeping-your-account-and-data-secure/creating-a-personal-access-token)

## 💡 What is the needed structure of the url for GitHub content upload?
`https://api.github.com/repos/<your username or org name>/<repo name>/contents/<name of file to upload>"`

## 💡 How do I make a GitHub file upload request using Volley
```kotlin
val requestQueue = Volley.newRequestQueue(this)

val body = JSONObject()
body.put("message", "<your desired commit message>") // commit message of file upload to GitHub
body.put("content", noteContent)

val request =  object : JsonObjectRequest(
  Method.PUT,
  "<your upload url>",
  body,
  {
    // handle success
  },
  {
    // handle error
  }
) {
  override fun getHeaders(): MutableMap<String, String> {
    return mutableMapOf(
      "Authorization" to "token <your token>",
      "Accept" to "application/vnd.github.v3+json"
    )
  }
}

requestQueue.add(request)
```

## 💡 How do I Base64 encode my Note data for upload?
Android has some utility methods for Base64 encoding.  We can leverage those.

```kotlin
val noteContent = note.title + "\n" + note.category + "\n" + note.content
return Base64.encodeToString(noteContent.toByteArray(), android.util.Base64.DEFAULT)
```

## 💡 Why am I getting a 404 when making my upload request to GitHub
You might need to double check that you are authenticating your request.

Ensure that you're adding a valid GitHub access token as a header to your request.

To do that with Volley, override the `getHeaders()` method of your `JsonObjectRequest`
```kotlin
override fun getHeaders(): MutableMap<String, String> {
    return mutableMapOf(
      "Authorization" to "token <your token here>"
    )
}
```

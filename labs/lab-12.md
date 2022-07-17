# 🖥 Lab 12: Fetch data from a network
Use Retrofit and OkHttp to load JSON data from a network api.

## Objectives

1. Open `AndroidManifest.xml` and add `<uses-permission android:name="android.permission.INTERNET" />`

3. Create a new package named `network`

2. In the `network` package, create an interface named `StudyGuideService`

3. Add a method to `StudyGuideService` called `getTopics()` that is a suspending function and returns a `List<Topic>`

4. Add the following annotation to the `Topic` class: `@JsonClass(generateAdapter = true)`
    1. This annotation will indicate to Moshi that it should generate a Json adapter automatically

4. We need to create an instance of `Retrofit.Builder` to generate a working network client
    1. Open `StudyGuideService.kt`
    2. Create a top-level property named `studyGuideService` and initialize it to `Retrofit.Builder().build()`
    3. Before the `.build()` call, add a call to `baseUrl(BuildConfig.STUDY_GUIDE_SERVICE_URL)`
    4. After setting the base url, add a json converter factory with `.addConverterFactory(MoshiConverterFactory.create())`

5. After calling `.build()` on `Retrofit.Builder`, create an instance of the `StudyGuideService` interface by calling `.create(StudyGuideService::class.java)`

6. Within the `init{}` of `StudyGuideViewModel`, replace the current implementation with one that uses the new Retrofit service to load the topics

# 🖥 Lab 12 Hints: Fetch data from a network

1. [Connecting to the internet](https://developer.android.com/training/basics/network-ops/connecting)
2. [Retrofit: A type-safe http client for Android and Java](https://square.github.io/retrofit/)
3. [Moshi](https://github.com/square/moshi/)

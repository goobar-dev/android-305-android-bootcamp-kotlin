package dev.goobar.androidstudyguide.network

import dev.goobar.androidstudyguide.BuildConfig
import dev.goobar.androidstudyguide.data.Topic
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val studyGuideService = Retrofit.Builder()
  .baseUrl(BuildConfig.STUDY_GUIDE_SERVICE_URL)
  .addConverterFactory(MoshiConverterFactory.create())
  .build()
  .create(StudyGuideService::class.java)

interface StudyGuideService {
  @GET("/topics")
  suspend fun getTopics(): List<Topic>
}
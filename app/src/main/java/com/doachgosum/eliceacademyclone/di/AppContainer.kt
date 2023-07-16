package com.doachgosum.eliceacademyclone.di

import android.app.Application
import com.doachgosum.eliceacademyclone.data.local.AppPreferences
import com.doachgosum.eliceacademyclone.data.remote.CourseApiService
import com.doachgosum.eliceacademyclone.data.remote.LectureApiService
import com.doachgosum.eliceacademyclone.data.remote.LoggingInterceptor
import com.doachgosum.eliceacademyclone.data.repository.CourseRepositoryImpl
import com.doachgosum.eliceacademyclone.data.repository.LectureRepositoryImpl
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AppContainer(application: Application) {

    private val baseUrl = "https://api-rest.elice.io/"

    private val client = OkHttpClient.Builder()
        .addInterceptor(LoggingInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(client)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val courseApi = retrofit.create(CourseApiService::class.java)
    private val lectureApi = retrofit.create(LectureApiService::class.java)

    private val prefs = AppPreferences(application)

    private val ioDispatcher = Dispatchers.IO

    val courseRepository = CourseRepositoryImpl(courseApi, ioDispatcher, prefs)
    val lectureRepository = LectureRepositoryImpl(lectureApi, ioDispatcher)

}
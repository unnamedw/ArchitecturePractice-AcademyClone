package com.doachgosum.eliceacademyclone.di

import com.doachgosum.eliceacademyclone.data.remote.CourseApiService
import com.doachgosum.eliceacademyclone.data.remote.LectureApiService
import com.doachgosum.eliceacademyclone.data.remote.LoggingInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class AppContainer {

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

    val courseApi = retrofit.create(CourseApiService::class.java)

    val lectureApi = retrofit.create(LectureApiService::class.java)

}
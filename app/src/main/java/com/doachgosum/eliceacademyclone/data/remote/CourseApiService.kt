package com.doachgosum.eliceacademyclone.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface CourseApiService {

    /**
     * paramJson: FilterConditionRequestParam as raw json
     * **/
    @GET("org/academy/course/list/")
    suspend fun getAllCourse(
        @Query("offset") offset: Int,
        @Query("count") count: Int,
        @Query("filter_is_recommended") filterIsRecommended: Boolean,
        @Query("filter_is_free") filterIsFree: Boolean,
        @Query("filter_conditions", encoded = true) paramJson: String,
    ): String

    @GET("org/academy/course/get/")
    suspend fun getCourseDetail(
        @Query("course_id") courseId: Int
    ): String

}
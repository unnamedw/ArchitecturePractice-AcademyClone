package com.doachgosum.eliceacademyclone.domain.repository

interface CourseRepository {

    suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean,
        filterIsFree: Boolean,
        filterConditions: List<Int>,
    )

    suspend fun getCourseDetail(courseId: Int)
}
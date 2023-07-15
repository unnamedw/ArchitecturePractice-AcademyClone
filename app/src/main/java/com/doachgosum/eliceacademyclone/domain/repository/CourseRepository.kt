package com.doachgosum.eliceacademyclone.domain.repository

import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.domain.model.CourseModel

interface CourseRepository {

    suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean,
        filterIsFree: Boolean,
        filterCondition: FilterConditionRequestParam? = null,
    ): List<CourseModel>

    suspend fun getCourseDetail(courseId: Int)

    fun getMyCourseIds(): Set<Int>

    fun saveCourseId(id: Int)
}
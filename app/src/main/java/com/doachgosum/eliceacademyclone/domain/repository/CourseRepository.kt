package com.doachgosum.eliceacademyclone.domain.repository

import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.domain.model.CourseModel
import kotlinx.coroutines.flow.Flow

interface CourseRepository {
    suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean? = null,
        filterIsFree: Boolean? = null,
        filterCondition: FilterConditionRequestParam? = null,
    ): List<CourseModel>

    suspend fun getCourseDetail(courseId: Int): CourseModel

    fun getMyCourseIds(): Set<Int>

    fun getMyCourseIdsFlow(): Flow<Set<Int>>

    fun saveCourseId(id: Int)

    fun deleteCourseId(id: Int)
}
package com.doachgosum.eliceacademyclone.data.repository

import android.util.Log
import com.doachgosum.eliceacademyclone.constant.LogTag
import com.doachgosum.eliceacademyclone.data.local.AppPreferences
import com.doachgosum.eliceacademyclone.data.remote.CourseApiService
import com.doachgosum.eliceacademyclone.data.remote.dto.toDomainModel
import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.domain.model.CourseModel
import com.doachgosum.eliceacademyclone.domain.repository.CourseRepository
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.net.URLEncoder
import java.nio.charset.Charset

class CourseRepositoryImpl(
    private val courseApiService: CourseApiService,
    private val ioDispatcher: CoroutineDispatcher,
    private val prefs: AppPreferences
): CourseRepository {

    override suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean?,
        filterIsFree: Boolean?,
        filterCondition: FilterConditionRequestParam?
    ): List<CourseModel> = withContext(ioDispatcher) {

        Log.d(LogTag.TAG_DEBUG, "param >> $filterCondition")

        return@withContext courseApiService.getAllCourse(
            offset = offset,
            count = count,
            filterIsRecommended = filterIsRecommended,
            filterIsFree = filterIsFree,
            filterConditionAsJson = filterCondition
                ?.let { gson.toJson(it) }
        ).courses
            .map { it.toDomainModel() }
    }

    override suspend fun getCourseDetail(courseId: Int): CourseModel = withContext(ioDispatcher) {
        return@withContext courseApiService.getCourseDetail(courseId)
            .course
            .toDomainModel()
    }

    override fun getMyCourseIds(): Set<Int> {
        return prefs.getMyCourseIds()
    }

    override fun getMyCourseIdsFlow(): Flow<Set<Int>> {
        return prefs.courseIdFlow
    }

    override fun saveCourseId(id: Int) {
        val oldIds = prefs.getMyCourseIds()
        val newIds = oldIds.toMutableSet()
            .apply { add(id) }

        prefs.setMyCourseIds(newIds)
    }

    override fun deleteCourseId(id: Int) {
        val oldIds = prefs.getMyCourseIds()
        val newIds = oldIds.toMutableSet()
            .apply { remove(id) }

        prefs.setMyCourseIds(newIds)
    }

    companion object {
        private val gson = Gson()
    }
}
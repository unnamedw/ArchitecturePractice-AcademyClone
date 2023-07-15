package com.doachgosum.eliceacademyclone.data.repository

import com.doachgosum.eliceacademyclone.domain.repository.CourseRepository

class CourseRepositoryImpl: CourseRepository {
    override suspend fun getCourseList(
        offset: Int,
        count: Int,
        filterIsRecommended: Boolean,
        filterIsFree: Boolean,
        filterConditions: List<Int>
    ) {
        TODO("Not yet implemented")
    }

    override suspend fun getCourseDetail(courseId: Int) {
        TODO("Not yet implemented")
    }
}
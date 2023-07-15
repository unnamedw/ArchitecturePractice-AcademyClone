package com.doachgosum.eliceacademyclone.domain.repository

import com.doachgosum.eliceacademyclone.domain.model.LectureModel

interface LectureRepository {
    suspend fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ): List<LectureModel>
}
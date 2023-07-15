package com.doachgosum.eliceacademyclone.domain.repository

interface LectureRepository {

    suspend fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    )
}
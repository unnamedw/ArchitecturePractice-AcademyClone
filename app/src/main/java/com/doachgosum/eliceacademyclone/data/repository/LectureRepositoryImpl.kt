package com.doachgosum.eliceacademyclone.data.repository

import com.doachgosum.eliceacademyclone.data.remote.LectureApiService
import com.doachgosum.eliceacademyclone.domain.repository.LectureRepository
import kotlinx.coroutines.CoroutineDispatcher

class LectureRepositoryImpl(
    private val lectureApiService: LectureApiService,
    private val ioDispatcher: CoroutineDispatcher
): LectureRepository {
    override suspend fun getLectureList(offset: Int, count: Int, courseId: Int) {
        TODO("Not yet implemented")
    }
}
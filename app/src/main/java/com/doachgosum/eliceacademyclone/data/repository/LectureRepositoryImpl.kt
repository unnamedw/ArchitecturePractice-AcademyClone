package com.doachgosum.eliceacademyclone.data.repository

import com.doachgosum.eliceacademyclone.data.remote.LectureApiService
import com.doachgosum.eliceacademyclone.data.remote.dto.toDomainModel
import com.doachgosum.eliceacademyclone.domain.model.LectureModel
import com.doachgosum.eliceacademyclone.domain.repository.LectureRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class LectureRepositoryImpl(
    private val lectureApiService: LectureApiService,
    private val ioDispatcher: CoroutineDispatcher
): LectureRepository {

    override suspend fun getLectureList(
        offset: Int,
        count: Int,
        courseId: Int
    ): List<LectureModel> = withContext(ioDispatcher) {
        return@withContext lectureApiService.getLectureList(offset, count, courseId)
            .lectures
            .map { it.toDomainModel() }
    }
}
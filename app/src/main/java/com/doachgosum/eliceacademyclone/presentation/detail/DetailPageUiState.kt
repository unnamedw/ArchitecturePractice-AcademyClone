package com.doachgosum.eliceacademyclone.presentation.detail

import com.doachgosum.eliceacademyclone.domain.model.CourseModel
import com.doachgosum.eliceacademyclone.domain.model.LectureModel
import com.doachgosum.eliceacademyclone.presentation.detail.adapter.LectureItemUiState

sealed class DetailPageUiState {

    object Loading: DetailPageUiState()

    data class Success(
        val course: CourseModel,
        val lectures: List<LectureItemUiState>
    ): DetailPageUiState()

    data class Error(val errorMsg: String): DetailPageUiState()
}
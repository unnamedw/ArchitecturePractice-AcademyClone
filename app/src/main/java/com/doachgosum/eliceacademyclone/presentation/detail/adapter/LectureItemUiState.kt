package com.doachgosum.eliceacademyclone.presentation.detail.adapter

import com.doachgosum.eliceacademyclone.domain.model.LectureModel

data class LectureItemUiState(
    val lecture: LectureModel,
    val showTopLine: Boolean,
    val showBottomLine: Boolean
)

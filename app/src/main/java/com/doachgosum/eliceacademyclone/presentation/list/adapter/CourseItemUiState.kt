package com.doachgosum.eliceacademyclone.presentation.list.adapter

import com.doachgosum.eliceacademyclone.domain.model.CourseModel

data class CourseItemUiState(
    val course: CourseModel,
    val onClick: (CourseModel) -> Unit
)
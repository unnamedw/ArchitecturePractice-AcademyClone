package com.doachgosum.eliceacademyclone.domain.model

data class CourseModel(
    val id: Int,
    val title: String,
    val imgUrl: String?,
    val shortDescription: String,
    val description: String?,
    val tags: List<String>
)
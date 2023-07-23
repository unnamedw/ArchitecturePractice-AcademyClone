package com.doachgosum.eliceacademyclone.presentation.list

sealed class CourseListPageEvent {
    data class MoveToDetailPage(val courseId: Int): CourseListPageEvent()
}
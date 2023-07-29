package com.doachgosum.eliceacademyclone.presentation.list

sealed class CourseListPageEvent {
    data class ClickItem(val courseId: Int): CourseListPageEvent()
}
package com.doachgosum.eliceacademyclone.presentation.list

sealed class MainPageEvent {
    data class MoveToDetailPage(val courseId: Int): MainPageEvent()
}
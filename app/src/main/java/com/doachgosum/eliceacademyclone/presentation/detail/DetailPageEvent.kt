package com.doachgosum.eliceacademyclone.presentation.detail

sealed class DetailPageEvent {
    data class CompleteApply(val msg: String): DetailPageEvent()
}
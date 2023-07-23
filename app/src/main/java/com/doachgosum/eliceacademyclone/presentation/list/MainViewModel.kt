package com.doachgosum.eliceacademyclone.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.doachgosum.eliceacademyclone.constant.CourseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(): ViewModel() {

    private val _courseTypes: MutableStateFlow<List<CourseType>> = MutableStateFlow(
        listOf(CourseType.FREE, CourseType.RECOMMEND, CourseType.MY)
    )
    val courseType = _courseTypes.asStateFlow()

    class Factory(): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel() as T
        }
    }
}
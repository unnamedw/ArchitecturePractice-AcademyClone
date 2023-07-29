package com.doachgosum.eliceacademyclone.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.doachgosum.eliceacademyclone.constant.CourseType
import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.domain.model.CourseModel
import com.doachgosum.eliceacademyclone.domain.repository.CourseRepository
import com.doachgosum.eliceacademyclone.presentation.list.adapter.CourseItemUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CourseListViewModel(
    private val courseType: CourseType,
    private val courseRepository: CourseRepository
): ViewModel() {

    private val _event: MutableSharedFlow<CourseListPageEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _courseList: MutableStateFlow<List<CourseItemUiState>> = MutableStateFlow(emptyList())
    val courseList = _courseList.asStateFlow()

    private var myCourse = courseRepository.getMyCourseIds()
    private var coursePage = 0

    private var fetchCourseListJob: Job? = null

    init {
        fetchNextList()
    }

    fun reloadList() {
        coursePage = 0
        myCourse = courseRepository.getMyCourseIds()
        _courseList.value = emptyList()
        fetchNextList()
    }

    fun fetchNextList() {
        if (fetchCourseListJob?.isActive == true) {
            return
        }

        fetchCourseListJob = viewModelScope.launch {

            val nextPageCount = if (courseType == CourseType.MY) {
                if (coursePage < myCourse.size) {
                    if (myCourse.size - coursePage > COUNT_PER_PAGE) {
                        COUNT_PER_PAGE
                    } else {
                        myCourse.size - coursePage
                    }
                } else {
                    return@launch
                }
            } else {
                COUNT_PER_PAGE
            }

            kotlin.runCatching {

                val filterCondition = if (courseType == CourseType.MY) {
                    FilterConditionRequestParam(
                        courseIds = courseRepository.getMyCourseIds()
                    )
                } else {
                    null
                }

                val filterIsRecommended = if (courseType == CourseType.RECOMMEND) true else null
                val filterIsFree = if (courseType == CourseType.FREE) true else null

                val nextList = courseRepository.getCourseList(
                    offset = coursePage,
                    count = nextPageCount,
                    filterIsRecommended = filterIsRecommended,
                    filterIsFree = filterIsFree,
                    filterCondition = filterCondition
                ).map { course ->
                    CourseItemUiState(
                        course = course,
                        onClick = ::onCourseItemClick
                    )
                }

                _courseList.value = _courseList.value.plus(nextList)

            }.onSuccess {
                coursePage += COUNT_PER_PAGE
            }.onFailure {
                it.printStackTrace()
            }.also {
                fetchCourseListJob = null
            }
        }

    }

    private fun onCourseItemClick(course: CourseModel) {
        viewModelScope.launch {
            _event.emit(CourseListPageEvent.ClickItem(courseId = course.id))
        }
    }

    companion object {
        private const val COUNT_PER_PAGE = 10
    }

    class Factory(
        private val courseType: CourseType,
        private val courseRepository: CourseRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return CourseListViewModel(courseType = courseType, courseRepository = courseRepository) as T
        }
    }
}
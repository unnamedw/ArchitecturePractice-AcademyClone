package com.doachgosum.eliceacademyclone.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.doachgosum.eliceacademyclone.data.remote.request_param.FilterConditionRequestParam
import com.doachgosum.eliceacademyclone.domain.model.CourseModel
import com.doachgosum.eliceacademyclone.domain.repository.CourseRepository
import com.doachgosum.eliceacademyclone.presentation.list.adapter.CourseItemUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val courseRepository: CourseRepository
): ViewModel() {

    private val _event: MutableSharedFlow<MainPageEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _freeCourseList: MutableStateFlow<List<CourseItemUiState>> = MutableStateFlow(emptyList())
    val freeCourseList = _freeCourseList.asStateFlow()

    private val _recommendCourseList: MutableStateFlow<List<CourseItemUiState>> = MutableStateFlow(emptyList())
    val recommendCourseList = _recommendCourseList.asStateFlow()

    private val _myCourseList: MutableStateFlow<List<CourseItemUiState>> = MutableStateFlow(emptyList())
    val myCourseList = _myCourseList.asStateFlow()

    private val myCourse = courseRepository.getMyCourseIds()

    private var fetchFreeCourseJob: Job? = null
    private var fetchRecommendCourseJob: Job? = null
    private var fetchMyCourseJob: Job? = null

    private var freeCoursePage = 0
    private var recommendCoursePage = 0
    private var myCoursePage = myCourse.size


    init {
        fetchFreeCourseList()
        fetchRecommendCourseList()
        fetchMyCourseList()
    }

    fun fetchFreeCourseList() {
        if (fetchFreeCourseJob?.isActive == true) return

        fetchFreeCourseJob = viewModelScope.launch {
            kotlin.runCatching {

                val nextList = courseRepository.getCourseList(
                    offset = freeCoursePage,
                    count = COUNT_PER_PAGE,
                    filterIsRecommended = false,
                    filterIsFree = true
                ).map { course ->
                    CourseItemUiState(
                        course = course,
                        onClick = ::onCourseItemClick
                    )
                }

                _freeCourseList.value = _freeCourseList.value.plus(nextList)

            }.onSuccess {
                freeCoursePage += COUNT_PER_PAGE
            }.onFailure {
                it.printStackTrace()
            }.also {
                fetchFreeCourseJob = null
            }
        }
    }

    fun fetchRecommendCourseList() {
        if (fetchRecommendCourseJob?.isActive == true) return

        fetchRecommendCourseJob = viewModelScope.launch {
            kotlin.runCatching {

                val nextList = courseRepository.getCourseList(
                    offset = recommendCoursePage,
                    count = COUNT_PER_PAGE,
                    filterIsRecommended = true,
                    filterIsFree = false
                ).map { course ->
                    CourseItemUiState(
                        course = course,
                        onClick = ::onCourseItemClick
                    )
                }

                _recommendCourseList.value = _recommendCourseList.value.plus(nextList)

            }.onSuccess {
                recommendCoursePage += COUNT_PER_PAGE
            }.onFailure {
                it.printStackTrace()
            }.also {
                fetchRecommendCourseJob = null
            }
        }
    }

    fun fetchMyCourseList() {
        if (fetchMyCourseJob?.isActive == true) return

        fetchMyCourseJob = viewModelScope.launch {
            kotlin.runCatching {

                val nextList = courseRepository.getCourseList(
                    offset = myCoursePage,
                    count = COUNT_PER_PAGE,
                    filterCondition = FilterConditionRequestParam(
                        courseIds = myCourse
                    )
                ).map { course ->
                    CourseItemUiState(
                        course = course,
                        onClick = ::onCourseItemClick
                    )
                }

                _myCourseList.value = _myCourseList.value.plus(nextList)

            }.onSuccess {
                myCoursePage += COUNT_PER_PAGE
            }.onFailure {
                it.printStackTrace()
            }.also {
                fetchMyCourseJob = null
            }
        }
    }

    private fun onCourseItemClick(course: CourseModel) {
        viewModelScope.launch {
            _event.emit(MainPageEvent.MoveToDetailPage(courseId = course.id))
        }
    }

    class Factory(
        private val courseRepository: CourseRepository
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(courseRepository) as T
        }
    }

    companion object {
        private const val COUNT_PER_PAGE = 10
    }
}
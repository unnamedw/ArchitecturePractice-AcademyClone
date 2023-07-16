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

    private var myCourse = courseRepository.getMyCourseIds()

    private var fetchFreeCourseJob: Job? = null
    private var fetchRecommendCourseJob: Job? = null
    private var fetchMyCourseJob: Job? = null

    private var freeCoursePage = 0
    private var recommendCoursePage = 0
    private var myCoursePage = 0

    init {
        reloadData()
    }

    fun reloadData() {
        freeCoursePage = 0
        recommendCoursePage = 0
        myCoursePage = 0

        myCourse = courseRepository.getMyCourseIds()

        _freeCourseList.value = emptyList()
        _recommendCourseList.value = emptyList()
        _myCourseList.value = emptyList()

        fetchNextFreeCourseList()
        fetchNextRecommendCourseList()
        fetchNextMyCourseList()
    }

    fun fetchNextFreeCourseList() {
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

    fun fetchNextRecommendCourseList() {
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

    fun fetchNextMyCourseList() {
        if (fetchMyCourseJob?.isActive == true) return

        fetchMyCourseJob = viewModelScope.launch {

            val realPageCount = if (myCoursePage < myCourse.size) {
                if (myCourse.size - myCoursePage > COUNT_PER_PAGE) {
                    COUNT_PER_PAGE
                } else {
                    myCourse.size - myCoursePage
                }
            } else {
                return@launch
            }

            kotlin.runCatching {

                val nextList = courseRepository.getCourseList(
                    offset = myCoursePage,
                    count = realPageCount,
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
                myCoursePage = realPageCount
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
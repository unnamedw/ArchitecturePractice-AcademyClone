package com.doachgosum.eliceacademyclone.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.doachgosum.eliceacademyclone.domain.repository.CourseRepository
import com.doachgosum.eliceacademyclone.domain.repository.LectureRepository
import com.doachgosum.eliceacademyclone.presentation.detail.adapter.LectureItemUiState
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailViewModel(
    private val courseId: Int,
    private val courseRepository: CourseRepository,
    private val lectureRepository: LectureRepository
): ViewModel() {

    private val _uiState: MutableStateFlow<DetailPageUiState> = MutableStateFlow(DetailPageUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<DetailPageEvent> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    val isApplied: StateFlow<Boolean> = courseRepository.getMyCourseIdsFlow()
        .map { it.contains(courseId) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(3000),
            initialValue = courseRepository.getMyCourseIds().contains(courseId)
        )

    private var fetchCourseDetailJob: Job? = null

    init {
        fetchCourseDetail()
    }

    private fun fetchCourseDetail() {
        if (fetchCourseDetailJob?.isActive == true) return

        fetchCourseDetailJob = viewModelScope.launch {
            kotlin.runCatching {
                _uiState.value = DetailPageUiState.Loading

                val courseAsync = async { courseRepository.getCourseDetail(courseId) }
                val lectureListAsync = async { lectureRepository.getLectureList(0, 40, courseId) }

                _uiState.value = DetailPageUiState.Success(
                    course = courseAsync.await(),
                    lectures = lectureListAsync.await().let { lectures ->

                        lectures.mapIndexed { index, lecture ->
                            LectureItemUiState(
                                lecture = lecture,
                                showTopLine = index != 0,
                                showBottomLine = index != lectures.lastIndex
                            )
                        }
                    }

                )

            }.onFailure {
                _uiState.value = DetailPageUiState.Error("정보를 불러오는 도중 문제가 발생했어요")
            }.also {
                fetchCourseDetailJob = null
            }
        }
    }

    fun clickApply() {
        val msg: String = if (isApplied.value) {
            courseRepository.deleteCourseId(id = courseId)
            "수강 취소되었습니다"
        } else {
            courseRepository.saveCourseId(id = courseId)
            "수강 완료되었습니다"
        }

        viewModelScope.launch {
            _event.emit(DetailPageEvent.CompleteApply(msg = msg))
        }
    }

    class Factory(
        private val courseId: Int,
        private val courseRepository: CourseRepository,
        private val lectureRepository: LectureRepository
    ): ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailViewModel(courseId, courseRepository, lectureRepository) as T
        }
    }
}
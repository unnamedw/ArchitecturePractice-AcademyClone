package com.doachgosum.eliceacademyclone.presentation.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.doachgosum.eliceacademyclone.R
import com.doachgosum.eliceacademyclone.databinding.ActivityMainBinding
import com.doachgosum.eliceacademyclone.presentation.detail.DetailFragment
import com.doachgosum.eliceacademyclone.presentation.list.adapter.CourseListAdapter
import com.doachgosum.eliceacademyclone.presentation.util.getAppContainer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * TODO(Course layout -> Fragment 로 모듈화)
 * **/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels {
        MainViewModel.Factory(
            getAppContainer().courseRepository
        )
    }

    private val freeCourseAdapter = CourseListAdapter()
    private val recommendCourseAdapter = CourseListAdapter()
    private val myCourseAdapter = CourseListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeViewModel()
        initView()
    }

    private fun subscribeViewModel() {

        lifecycleScope.launch {
            viewModel.event
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { event ->
                    when (event) {
                        is MainPageEvent.MoveToDetailPage -> moveToDetailPage(event.courseId)
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.freeCourseList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    freeCourseAdapter.submitList(it)
                }
        }

        lifecycleScope.launch {
            viewModel.recommendCourseList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    recommendCourseAdapter.submitList(it)
                }
        }

        lifecycleScope.launch {
            viewModel.myCourseList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    myCourseAdapter.submitList(it)
                }
        }
    }

    private fun initView() {
        binding.layoutFreeCourseList.apply {
            tvListTitle.text = getString(R.string.title_free_course)
            rvCourseList.adapter = freeCourseAdapter
            rvCourseList.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val canScrollDown = rvCourseList.canScrollHorizontally(1)
                    val canBeIgnored = dy==0 && dx==0

                    if (!canScrollDown && !canBeIgnored) {
                        viewModel.fetchFreeCourseList()
                    }
                }
            })
        }

        binding.layoutRecommendCourseList.apply {
            tvListTitle.text = getString(R.string.title_recommend_course)
            rvCourseList.adapter = recommendCourseAdapter
            rvCourseList.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val canScrollDown = rvCourseList.canScrollHorizontally(1)
                    val canBeIgnored = dy==0 && dx==0

                    if (!canScrollDown && !canBeIgnored) {
                        viewModel.fetchRecommendCourseList()
                    }
                }
            })
        }

        binding.layoutMyCourseList.apply {
            tvListTitle.text = getString(R.string.title_my_course)
            rvCourseList.adapter = myCourseAdapter
            rvCourseList.addOnScrollListener(object : OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    val canScrollDown = rvCourseList.canScrollHorizontally(1)
                    val canBeIgnored = dy==0 && dx==0

                    if (!canScrollDown && !canBeIgnored) {
                        viewModel.fetchMyCourseList()
                    }
                }
            })
        }
    }

    private fun moveToDetailPage(courseId: Int) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailFragment.newInstance(courseId))
            .commit()
    }
}
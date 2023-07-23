package com.doachgosum.eliceacademyclone.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.doachgosum.eliceacademyclone.R
import com.doachgosum.eliceacademyclone.constant.CourseType
import com.doachgosum.eliceacademyclone.databinding.LayoutCourseListHorizontalBinding
import com.doachgosum.eliceacademyclone.presentation.detail.DetailFragment
import com.doachgosum.eliceacademyclone.presentation.list.adapter.CourseListAdapter
import com.doachgosum.eliceacademyclone.presentation.util.getAppContainer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class CourseListFragment: Fragment() {

    private lateinit var courseType: CourseType

    private lateinit var viewModel: CourseListViewModel
    private lateinit var binding: LayoutCourseListHorizontalBinding

    private val courseAdapter = CourseListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        courseType = requireArguments().getSerializable(PARAM_COURSE_TYPE) as CourseType

        viewModel = ViewModelProvider(this, CourseListViewModel.Factory(
            courseType,
            courseRepository = getAppContainer().courseRepository
        ))[CourseListViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = LayoutCourseListHorizontalBinding.inflate(inflater, container, false)

        subscribeViewModel()
        initView()

        return binding.root
    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewModel.event
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { event ->
                    when (event) {
                        is CourseListPageEvent.MoveToDetailPage -> moveToDetailPage(event.courseId)
                    }
                }
        }

        lifecycleScope.launch {
            viewModel.courseList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    courseAdapter.submitList(it)
                }
        }
    }

    private fun initView() {
        binding.tvListTitle.text = courseType.title
        binding.rvCourseList.adapter = courseAdapter
        binding.rvCourseList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val canScrollDown = binding.rvCourseList.canScrollHorizontally(1)
                val canBeIgnored = dy==0 && dx==0

                if (!canScrollDown && !canBeIgnored) {
                    viewModel.fetchNextList()
                }
            }
        })
    }

    private fun moveToDetailPage(courseId: Int) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailFragment.newInstance(courseId))
            .commit()
    }

    fun refreshList() {
        viewModel.reloadList()
    }

    companion object {

        private const val PARAM_COURSE_TYPE = "param_course_type"

        fun newInstance(type: CourseType): CourseListFragment {
            return CourseListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(PARAM_COURSE_TYPE, type)
                }
            }
        }
    }
}
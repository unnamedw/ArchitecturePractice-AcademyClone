package com.doachgosum.eliceacademyclone.presentation.list

import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.doachgosum.eliceacademyclone.constant.CourseType
import com.doachgosum.eliceacademyclone.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * TODO(Course layout -> Fragment 로 모듈화)
 * **/
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainViewModel by viewModels { MainViewModel.Factory() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        lifecycleScope.launch {
            viewModel.courseType
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest {
                    setUpList(it)
                }
        }
    }

    private fun setUpList(types: List<CourseType>) {
        binding.layoutCourseListContainer.removeAllViews()

        val fragments = types.map { CourseListFragment.newInstance(type = it) }

        binding.swipeContainer.setOnRefreshListener {
            binding.swipeContainer.isRefreshing = false
            fragments.forEach { it.refreshList() }
        }

        fragments.forEach { fragment ->
            val frameLayout = FrameLayout(this).apply {
                layoutParams = FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )

                id = hashCode()
            }

            supportFragmentManager.beginTransaction()
                .replace(frameLayout.id, fragment)
                .commit()

            binding.layoutCourseListContainer.addView(frameLayout)
        }
    }

}
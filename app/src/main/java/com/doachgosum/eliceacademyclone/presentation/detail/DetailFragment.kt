package com.doachgosum.eliceacademyclone.presentation.detail

import android.content.Context
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.doachgosum.eliceacademyclone.R
import com.doachgosum.eliceacademyclone.databinding.FragmentDetailBinding
import com.doachgosum.eliceacademyclone.presentation.detail.adapter.LectureListAdapter
import com.doachgosum.eliceacademyclone.presentation.util.getAppContainer
import io.noties.markwon.Markwon
import io.noties.markwon.SpannableBuilder
import io.noties.markwon.image.glide.GlideImagesPlugin
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DetailFragment: Fragment() {
    private val onBackPressedCallback: OnBackPressedCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        }
    }
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels {
        DetailViewModel.Factory(
            requireArguments().getInt(PARAM_COURSE_ID),
            getAppContainer().courseRepository,
            getAppContainer().lectureRepository
        )
    }

    private val lectureListAdapter = LectureListAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        subscribeViewModel()
        initView()

        return binding.root
    }

    private fun subscribeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { uiState ->
                    when (uiState) {
                        is DetailPageUiState.Loading -> {
                            binding.layoutLoading.visibility = View.VISIBLE
                            binding.layoutError.visibility = View.GONE
                            binding.layoutSuccess.visibility = View.GONE
                        }
                        is DetailPageUiState.Success -> {
                            binding.layoutLoading.visibility = View.GONE
                            binding.layoutError.visibility = View.GONE
                            binding.layoutSuccess.visibility = View.VISIBLE

                            updateUi(uiState)
                        }
                        is DetailPageUiState.Error -> {
                            binding.layoutLoading.visibility = View.GONE
                            binding.layoutError.visibility = View.VISIBLE
                            binding.layoutSuccess.visibility = View.GONE

                            binding.tvErrorMessage.text = uiState.errorMsg
                        }
                    }
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.isApplied
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { isApplied ->

                    if (isApplied) {
                        binding.tvBtnApply.text = "수강 취소"
                        binding.cvBtnApply.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_F44336))
                    } else {
                        binding.tvBtnApply.text = "수강 신청"
                        binding.cvBtnApply.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.color_5A2ECC))
                    }

                }
        }
    }

    private fun initView() {
        binding.rvLectureList.apply {
            adapter = lectureListAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        binding.ivBackDetail.setOnClickListener {
            finish()
        }

        binding.cvBtnApply.setOnClickListener { viewModel.clickApply() }
    }

    private fun finish() {
        requireActivity().supportFragmentManager.beginTransaction()
            .remove(this)
            .commit()
    }

    private fun updateUi(uiState: DetailPageUiState.Success) {

        // update title bar
        if (uiState.course.imgUrl.isNullOrEmpty()) {
            binding.titleWithImage.root.visibility = View.GONE
            binding.titleWithoutImage.root.visibility = View.VISIBLE

            binding.titleWithoutImage.tvTitle.text = uiState.course.title
            binding.titleWithoutImage.tvShortDescription.text = uiState.course.shortDescription
        } else {
            binding.titleWithImage.root.visibility = View.VISIBLE
            binding.titleWithoutImage.root.visibility = View.GONE

            binding.titleWithImage.tvTitle.text = uiState.course.title
            Glide.with(requireContext())
                .load(uiState.course.imgUrl)
                .into(binding.titleWithImage.ivTitleBar)
        }

        // update description
        if (uiState.course.description.isNullOrEmpty()) {
            binding.tvDescription.apply {
                visibility = View.GONE
            }
        } else {
            binding.tvDescription.apply {
                visibility = View.VISIBLE
                val markwon = Markwon.builder(requireContext())
                    .usePlugin(GlideImagesPlugin.create(requireContext()))
                    .build()
                markwon.setMarkdown(this, uiState.course.description)
            }
        }

        // update lecture
        lectureListAdapter.submitList(uiState.lectures)
    }

    companion object {
        private const val PARAM_COURSE_ID = "param_course_id"

        fun newInstance(courseId: Int): DetailFragment {
            val bundle = Bundle()
                .apply {
                    putInt(PARAM_COURSE_ID, courseId)
                }

            return DetailFragment()
                .apply {
                    arguments = bundle
                }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(onBackPressedCallback)
    }

    override fun onDetach() {
        super.onDetach()
        onBackPressedCallback.remove()
    }

}
package com.doachgosum.eliceacademyclone.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.doachgosum.eliceacademyclone.databinding.FragmentDetailBinding

class DetailFragment: Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val courseId: Int by lazy { requireArguments().getInt(PARAM_COURSE_ID) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        initView()

        return binding.root
    }

    private fun initView() {
        binding.ivBackDetail.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .remove(this)
                .commit()
        }
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
}
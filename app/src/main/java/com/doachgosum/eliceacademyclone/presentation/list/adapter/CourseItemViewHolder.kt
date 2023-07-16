package com.doachgosum.eliceacademyclone.presentation.list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.doachgosum.eliceacademyclone.databinding.ViewHolderCourseItemBinding
import com.doachgosum.eliceacademyclone.presentation.list.adapter.tag.TagListAdapter
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

class CourseItemViewHolder(
    private val binding: ViewHolderCourseItemBinding
): RecyclerView.ViewHolder(binding.root) {

    private val tagListAdapter = TagListAdapter()

    init {
        setUpTagList()
    }

    fun bind(uiState: CourseItemUiState) {
        binding.root.setOnClickListener {
            uiState.onClick.invoke(uiState.course)
        }

        binding.tvCourseTitle.text = uiState.course.title
        binding.tvCourseShortDescription.text = uiState.course.shortDescription

        if (uiState.course.imgUrl == null) {
            binding.frameDefaultImage.visibility = View.VISIBLE
            binding.ivCourseImage.visibility = View.INVISIBLE
        } else {
            binding.frameDefaultImage.visibility = View.INVISIBLE
            binding.ivCourseImage.visibility = View.VISIBLE

            Glide.with(binding.root.context)
                .load(uiState.course.imgUrl)
                .into(binding.ivCourseImage)
        }

        tagListAdapter.submitList(uiState.course.tags)

    }

    private fun setUpTagList() {
        binding.rvTagList.apply {
            layoutManager = FlexboxLayoutManager(binding.root.context).apply {
                flexWrap = FlexWrap.WRAP
            }
            adapter = tagListAdapter
        }
    }

}
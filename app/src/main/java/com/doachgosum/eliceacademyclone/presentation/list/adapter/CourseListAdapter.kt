package com.doachgosum.eliceacademyclone.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.doachgosum.eliceacademyclone.databinding.ViewHolderCourseItemBinding

class CourseListAdapter: ListAdapter<CourseItemUiState, CourseItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CourseItemUiState>() {
            override fun areItemsTheSame(oldItem: CourseItemUiState, newItem: CourseItemUiState): Boolean {
                return oldItem.course.id == newItem.course.id
            }

            override fun areContentsTheSame(oldItem: CourseItemUiState, newItem: CourseItemUiState): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseItemViewHolder {
        return CourseItemViewHolder(
            ViewHolderCourseItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CourseItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
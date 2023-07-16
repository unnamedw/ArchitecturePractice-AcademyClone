package com.doachgosum.eliceacademyclone.presentation.list.adapter.tag

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.doachgosum.eliceacademyclone.databinding.LayoutCourseTagBinding

class TagListAdapter: ListAdapter<String, TagItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagItemViewHolder {
        return TagItemViewHolder(
            LayoutCourseTagBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TagItemViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }

}
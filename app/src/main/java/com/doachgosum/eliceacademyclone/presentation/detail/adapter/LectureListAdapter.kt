package com.doachgosum.eliceacademyclone.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.doachgosum.eliceacademyclone.databinding.ViewHolderLectureItemBinding

class LectureListAdapter: ListAdapter<LectureItemUiState, LectureItemViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<LectureItemUiState>() {
            override fun areItemsTheSame(oldItem: LectureItemUiState, newItem: LectureItemUiState): Boolean {
                return oldItem.lecture.id == newItem.lecture.id
            }

            override fun areContentsTheSame(oldItem: LectureItemUiState, newItem: LectureItemUiState): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LectureItemViewHolder {
        return LectureItemViewHolder(
            ViewHolderLectureItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LectureItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}
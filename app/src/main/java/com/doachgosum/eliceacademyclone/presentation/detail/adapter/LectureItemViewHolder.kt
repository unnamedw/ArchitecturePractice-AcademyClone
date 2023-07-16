package com.doachgosum.eliceacademyclone.presentation.detail.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.doachgosum.eliceacademyclone.databinding.ViewHolderLectureItemBinding

class LectureItemViewHolder(
    private val binding: ViewHolderLectureItemBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(uiState: LectureItemUiState) {
        binding.tvTitle.text = uiState.lecture.title
        binding.tvDescription.text = uiState.lecture.description

        binding.viewTopLine.visibility = if (uiState.showTopLine) View.VISIBLE else View.INVISIBLE
        binding.viewBottomLine.visibility = if (uiState.showBottomLine) View.VISIBLE else View.INVISIBLE
    }
}
package com.doachgosum.eliceacademyclone.presentation.list.adapter.tag

import androidx.recyclerview.widget.RecyclerView
import com.doachgosum.eliceacademyclone.databinding.LayoutCourseTagBinding

class TagItemViewHolder(
    private val binding: LayoutCourseTagBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(tag: String) {
        binding.tvTag.text = tag
    }
}
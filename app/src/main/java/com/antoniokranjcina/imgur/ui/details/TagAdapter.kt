package com.antoniokranjcina.imgur.ui.details

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.antoniokranjcina.imgur.data.network.model.Tag
import com.antoniokranjcina.imgur.databinding.ItemTagBinding

class TagAdapter : ListAdapter<Tag, TagAdapter.TagsViewHolder>(TAG_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagsViewHolder {
        val binding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TagsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagsViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    inner class TagsViewHolder(private val binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tag: Tag) {
            binding.apply {
                layout.setBackgroundColor(Color.parseColor("#${tag.backgroundColor}"))
                textView.text = tag.title
            }
        }
    }

    companion object {
        private val TAG_COMPARATOR = object : DiffUtil.ItemCallback<Tag>() {
            override fun areItemsTheSame(oldItem: Tag, newItem: Tag) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Tag, newItem: Tag) = oldItem == newItem
        }
    }
}
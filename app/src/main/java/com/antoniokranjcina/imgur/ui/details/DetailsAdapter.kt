package com.antoniokranjcina.imgur.ui.details

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.antoniokranjcina.imgur.R
import com.antoniokranjcina.imgur.data.network.model.Image
import com.antoniokranjcina.imgur.databinding.ItemDetailsBinding

class DetailsAdapter : ListAdapter<Image, DetailsAdapter.DetailsViewHolder>(DETAILS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding = ItemDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    inner class DetailsViewHolder(private val binding: ItemDetailsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image) {
            binding.apply {
                imageViewPostImage.load(image.link) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image_loading_background)
                }
                if (image.description != null && image.description.isNotEmpty()) {
                    textViewDescription.visibility = VISIBLE
                    textViewDescription.text = image.description
                } else {
                    textViewDescription.visibility = GONE
                }
            }
        }
    }

    companion object {
        private val DETAILS_COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Image, newItem: Image) = oldItem == newItem
        }
    }
}
package com.antoniokranjcina.imgur.ui.home

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.antoniokranjcina.imgur.R
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.ItemPostBinding

class PostsAdapter(private val listener: PostOnClickListener) : ListAdapter<Post, PostsAdapter.PostViewHolder>(POST_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val item = getItem(position)

        if (item != null) {
            holder.bind(item)
        }
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onPostClick(getItem(adapterPosition))
                }
            }
        }

        fun bind(post: Post) {
            binding.apply {
                val points = post.ups.toString() + " Points"
                textViewTitle.text = post.title
                textViewPoints.text = points

                val imagesCount = post.imagesCount
                if (imagesCount > 1) {
                    if (imagesCount >= 3) {
                        showImageCount(3)
                    } else {
                        showImageCount(imagesCount)
                    }
                } else {
                    hideImageCount()
                }

                try {
                    val type = post.images[0].type
                    val link = post.images[0].link
                    val gif = post.images[0].gif

                    if (type.contains("image", ignoreCase = true)) {
                        loadImage(link)
                    } else if (type.contains("video", ignoreCase = true)) {
                        val thumbnail = gif!!.substring(0, gif.length - 1)
                        loadImage(thumbnail)
                    }
                } catch (e: Exception) {
                }
            }
        }

        private fun showImageCount(imagesCount: Number) {
            binding.apply {
                textViewImagesCount.text = imagesCount.toString()
                imageViewImageCount.visibility = VISIBLE
                textViewImagesCount.visibility = VISIBLE
            }
        }

        private fun hideImageCount() {
            binding.apply {
                imageViewImageCount.visibility = GONE
                textViewImagesCount.visibility = GONE
            }
        }

        private fun loadImage(link: String) {
            binding.apply {
                imageViewPostImage.visibility = VISIBLE

                imageViewPostImage.load(link) {
                    crossfade(true)
                    placeholder(R.drawable.ic_image_loading_background)
                }
            }
        }
    }

    interface PostOnClickListener {
        fun onPostClick(post: Post)
    }

    companion object {
        private val POST_COMPARATOR = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post) = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Post, newItem: Post) = oldItem == newItem
        }
    }
}
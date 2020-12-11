package com.antoniokranjcina.imgur.ui.home

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.ItemPostBinding
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util

class PostsAdapter(private val listener: PostOnClickListener) :
    ListAdapter<Post, PostsAdapter.PostViewHolder>(POST_COMPARATOR) {

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

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.apply {
                root.setOnClickListener {
                    listener.onPostClick(getItem(adapterPosition))
                }
            }
        }

        fun bind(post: Post) {
            binding.apply {
                textViewTitle.text = post.title
                try {
                    val type = post.images[0].type

                    if (type.contains("image", ignoreCase = true)) {
                        imageView.visibility = View.VISIBLE
                        videoView.visibility = View.GONE
                        imageView.load(post.images[0].link)
                    } else if (type.contains("video", ignoreCase = true)) {
                        imageView.visibility = View.GONE
                        videoView.visibility = View.VISIBLE

                        val videoUrl = Uri.parse(post.images[0].link)
                        val player = ExoPlayerFactory.newSimpleInstance(videoView.context)
                        videoView.player = player
                        val dataSourceFactory: DataSource.Factory = DefaultDataSourceFactory(
                            videoView.context,
                            Util.getUserAgent(videoView.context, "App name")
                        )
                        val videoSource: MediaSource =
                            ProgressiveMediaSource.Factory(dataSourceFactory)
                                .createMediaSource(videoUrl)
                        player.prepare(videoSource)
                    }
                } catch (e: Exception) {
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
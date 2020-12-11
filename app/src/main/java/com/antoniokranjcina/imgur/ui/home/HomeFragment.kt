package com.antoniokranjcina.imgur.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.antoniokranjcina.imgur.data.network.api.ImgurApi
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), PostsAdapter.PostOnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val postAdapter = PostsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            requestPosts()
        }

        viewLifecycleOwner.lifecycle

        binding.apply {
            recyclerView.apply {
                adapter = postAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostClick(post: Post) {}

    private suspend fun requestPosts() {
        val responseList = ImgurApi.service.getImgurResponse()
        val posts = responseList.body()?.data?.posts

        Log.d(TAG, "requestPosts: $posts")

        postAdapter.submitList(posts)
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
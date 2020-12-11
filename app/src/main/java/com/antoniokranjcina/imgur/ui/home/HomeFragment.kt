package com.antoniokranjcina.imgur.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.antoniokranjcina.imgur.data.network.api.ImgurApi
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.FragmentHomeBinding
import com.antoniokranjcina.imgur.util.Constants.NO_INTERNET
import com.antoniokranjcina.imgur.util.Constants.UNEXPECTED_ERROR
import kotlinx.coroutines.launch
import java.io.IOException

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
        try {
            showLoadingAnim()

            val responseList = ImgurApi.service.getImgurResponse()
            val posts = responseList.body()?.data?.posts
            postAdapter.submitList(posts)

            hideLoadingAnim()

        } catch (e: IOException) {
            hideLoadingAnim()
            Toast.makeText(requireContext(), NO_INTERNET, Toast.LENGTH_LONG).show()
        } catch (e: Exception) {
            hideLoadingAnim()
            Toast.makeText(requireContext(), UNEXPECTED_ERROR, Toast.LENGTH_LONG).show()
        }
    }

    private fun hideLoadingAnim() {
        binding.apply {
            progressBar.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun showLoadingAnim() {
        binding.apply {
            progressBar.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }
}
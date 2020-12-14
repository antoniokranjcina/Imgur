package com.antoniokranjcina.imgur.ui.home

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.FragmentHomeBinding
import com.antoniokranjcina.imgur.repository.Repository
import com.antoniokranjcina.imgur.util.Constants.LOADING
import com.antoniokranjcina.imgur.util.Constants.NOT_LOADING
import com.antoniokranjcina.imgur.viewmodel.MainViewModel
import com.antoniokranjcina.imgur.viewmodel.MainViewModelFactory
import com.google.android.material.snackbar.Snackbar

class HomeFragment : Fragment(), PostsAdapter.PostOnClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val postAdapter = PostsAdapter(this)

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(Repository())).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeLoading()
        observeError()
        observeDataFromApi()

        binding.apply {
            recyclerView.apply {
                adapter = postAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onPostClick(post: Post) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(post, post.title)
        findNavController().navigate(action)
    }

    private fun observeLoading() {
        mainViewModel.loading.observe(viewLifecycleOwner, {
            if (it == LOADING) {
                binding.recyclerView.showShimmer()
            } else if (it == NOT_LOADING) {
                binding.recyclerView.hideShimmer()
            }
        })
    }

    private fun observeError() {
        mainViewModel.error.observe(viewLifecycleOwner, {
            Snackbar
                .make(requireView(), it, Snackbar.LENGTH_INDEFINITE)
                .setAction("Retry") {
                    mainViewModel.getPosts()
                }
                .setActionTextColor(Color.WHITE)
                .show()
        })
    }

    private fun observeDataFromApi() {
        mainViewModel.readPosts.observe(viewLifecycleOwner, {
            postAdapter.submitList(it)
        })
    }
}
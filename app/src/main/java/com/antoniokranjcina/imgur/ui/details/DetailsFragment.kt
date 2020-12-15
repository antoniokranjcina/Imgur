package com.antoniokranjcina.imgur.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.antoniokranjcina.imgur.data.network.model.Post
import com.antoniokranjcina.imgur.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val navArgs: DetailsFragmentArgs by navArgs()
    private lateinit var post: Post

    private val detailsAdapter = DetailsAdapter()
    private val tagAdapter = TagAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        post = navArgs.post
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireContext() as AppCompatActivity).supportActionBar!!.subtitle = post.accountUrl

        detailsAdapter.submitList(post.images)
        tagAdapter.submitList(post.tags)

//        Log.d(TAG, "onViewCreated: ${post.description}")

        binding.apply {
            recyclerViewImages.apply {
                isNestedScrollingEnabled = true
                adapter = detailsAdapter
            }

            recyclerViewTags.apply {
                isNestedScrollingEnabled = true
                adapter = tagAdapter
                layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            }
            val views = "${post.views} views"
            textViewNumberOfViews.text = views
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        (requireContext() as AppCompatActivity).supportActionBar!!.subtitle = ""
    }

    companion object {
        private const val TAG = "DetailsFragment"
    }
}
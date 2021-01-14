package com.tapok.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.PhotoAdapter
import com.tapok.unsplash.databinding.FragmentCollectionsBinding
import com.tapok.unsplash.model.CollectionsItem
import com.tapok.unsplash.viewmodel.CollectionsPhotoViewModel

class CollectionPhotoFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding
    private lateinit var collection: CollectionsItem
    private val viewModel: CollectionsPhotoViewModel by viewModels()
    private var photoAdapter: PhotoAdapter = PhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        collection = CollectionPhotoFragmentArgs.fromBundle(requireArguments()).collection
        binding.collection = collection
        bindRecyclerView()
        bindRefreshLayout()
        initObservers()
        savedInstanceState ?: viewModel.getPhoto(collection.id)
    }

    private fun bindRefreshLayout() {
        binding.swipeContainer.apply {
            setOnRefreshListener {
                isRefreshing = true
                viewModel.getPhoto(collection.id)
                isRefreshing = false
            }
        }
    }

    private fun initObservers() {
        viewModel.data.observe(viewLifecycleOwner) { result ->
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, result)
        }
    }

    private fun bindRecyclerView() {
        photoAdapter.clickListener = { photo ->
            findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(photo))
        }
        binding.photoList.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = photoAdapter
        }
    }
}
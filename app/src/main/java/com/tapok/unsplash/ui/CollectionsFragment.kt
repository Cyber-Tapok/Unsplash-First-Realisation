package com.tapok.unsplash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tapok.unsplash.CollectionsPhotoAdapter
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.databinding.FragmentCollectionsBinding
import com.tapok.unsplash.model.CollectionsItem
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.utils.PreCachingLayoutManager
import com.tapok.unsplash.viewmodel.CollectionsPhotoViewModel

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding
    private lateinit var collection: CollectionsItem
    private val viewModel: CollectionsPhotoViewModel by viewModels()
    private val photoAdapter = CollectionsPhotoAdapter()

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
        bindRecyclerView()
        collection = getCollection()
        binding.collection = collection
        viewModel.data.observe(viewLifecycleOwner) { result ->
            when (result) {
                DataState.Idle -> {
                }
                DataState.Start -> {
                }
                is DataState.Success -> {
                    Log.e("unsplash", result.data.toString())
                    photoAdapter.setData(result.data)
                }
                is DataState.Failed -> {
                    Log.e("ERROR", result.e.toString())
                }
            }
        }
        viewModel.loadData(collection.id)

    }

    private fun bindRecyclerView() {
        photoAdapter.clickListener = { photo ->
            findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(photo))
        }
        binding.photoList.apply {
            adapter = photoAdapter
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }

    private fun getCollection() = CollectionsFragmentArgs.fromBundle(requireArguments()).collection
}
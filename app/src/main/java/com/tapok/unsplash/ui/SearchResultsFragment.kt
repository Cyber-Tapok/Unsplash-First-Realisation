package com.tapok.unsplash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapok.unsplash.CollectionsPhotoAdapter
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.databinding.FragmentSearchResultsBinding
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.viewmodel.SearchPhotoViewModel

class SearchResultsFragment : Fragment() {
    private lateinit var binding: FragmentSearchResultsBinding
    private val viewModel: SearchPhotoViewModel by viewModels()
    private var photoAdapter: CollectionsPhotoAdapter = CollectionsPhotoAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.setOnEditorActionListener { view, id, event ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                view.clearFocus()
                Log.e("Search", "da")
                viewModel.loadData(view.text.toString())
            }
            true
        }
        bindRecyclerView()
        viewModel.data.observe(viewLifecycleOwner) { result ->
            Log.e("T", result.toString())
            when (result) {
                DataState.Idle -> {
                }
                DataState.Start -> {
                }
                is DataState.Success -> {
                    Log.e("unsplash", result.data.toString())
//                    photoAdapter.setData(result.data.results)
                }
                is DataState.Failed -> {
                    Log.e("ERROR", result.e.toString())
                }
            }
        }
    }

    private fun bindRecyclerView() {
//        photoAdapter = CollectionsPhotoAdapter()
//        photoAdapter.setHasStableIds(true)
        photoAdapter.clickListener = { photo ->
            findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(photo))
        }
        binding.photoList.apply {
            adapter = photoAdapter
//            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//            layoutManager = GridLayoutManager(requireContext(), 2)
//            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        }
    }
}
package com.tapok.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.PhotoAdapter
import com.tapok.unsplash.databinding.FragmentSearchResultsBinding
import com.tapok.unsplash.viewmodel.SearchPhotoViewModel

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchResultsBinding
    private val viewModel: SearchPhotoViewModel by viewModels()
    private var photoAdapter: PhotoAdapter = PhotoAdapter()

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
        binding.editText.setOnEditorActionListener { editView, id, _ ->
            if (id == EditorInfo.IME_ACTION_SEARCH) {
                editView.clearFocus()
                viewModel.searchPhotos(editView.text.toString())
            }
            true
        }
        bindRecyclerView()
        viewModel.data.observe(viewLifecycleOwner) { result ->
            photoAdapter.submitData(viewLifecycleOwner.lifecycle, result)
        }
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
}
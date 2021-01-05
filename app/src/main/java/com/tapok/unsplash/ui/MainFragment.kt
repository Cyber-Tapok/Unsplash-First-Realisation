package com.tapok.unsplash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.databinding.FragmentMainBinding
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.utils.getMessageId
import com.tapok.unsplash.viewmodel.CollectionsViewModel
import com.tapok.unsplash.viewmodel.RandomViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: RandomViewModel by viewModels()
    private val collectionsViewModel: CollectionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.data.observe(viewLifecycleOwner) { result ->
            binding.randomPhoto.layoutPhoto.layout.isVisible =
                result !is DataState.Idle && result !is DataState.Failed
            binding.randomPhoto.layoutError.layout.isVisible =
                result is DataState.Failed
            when (result) {
                DataState.Idle -> {
                }
                DataState.Start -> {
                }
                is DataState.Success -> {
                    Log.e("unsplash", result.data.toString())
                    binding.randomPhoto.layoutPhoto.photo = result.data
                }
                is DataState.Failed -> {
                    Log.e("ERROR", result.e.toString())
                    binding.randomPhoto.layoutError.textError.text =
                        getString(result.e.getMessageId())
                }
            }
        }
        collectionsViewModel.data.observe(viewLifecycleOwner) { result ->
            binding.collection.coverPhoto.layoutPhoto.layout.isVisible =
                result !is DataState.Idle && result !is DataState.Failed
            binding.collection.coverPhoto.layoutError.layout.isVisible =
                result is DataState.Failed
            when (result) {
                DataState.Idle -> {
                }
                DataState.Start -> {
                }
                is DataState.Success -> {
                    Log.e("unsplash", result.data.toString())
                    binding.collection.collection = result.data[1]
                    binding.collection.coverPhoto.layoutPhoto.photo = result.data[1].cover_photo
                }
                is DataState.Failed -> {
                    Log.e("ERROR", result.e.toString())
                    binding.collection.coverPhoto.layoutError.textError.text =
                        getString(result.e.getMessageId())
                }
            }
        }
        binding.viewModel = viewModel
        binding.randomPhoto.layoutPhoto.image.setOnClickListener {
            findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(binding.randomPhoto.layoutPhoto.photo!!))
        }
        if (viewModel.data.value is DataState.Idle) viewModel.loadData()
        if (collectionsViewModel.data.value is DataState.Idle) collectionsViewModel.loadData()
    }
}
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
import com.tapok.unsplash.repos.RandomRepository
import com.tapok.unsplash.viewmodel.RandomViewModel

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private val viewModel: RandomViewModel by viewModels()

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
            binding.test.layout.isVisible = result !is RandomRepository.DataState.Idle && result !is RandomRepository.DataState.Failed
            binding.errorLayout.layout.isVisible = result is RandomRepository.DataState.Failed
            when (result) {
                RandomRepository.DataState.Idle -> {
                }
                RandomRepository.DataState.Start -> {
                }
                is RandomRepository.DataState.Success -> {
                    Log.e("unsplash", result.data.toString())
                    binding.test.photo = result.data
                }
                is RandomRepository.DataState.Failed -> {
                    Log.e("ERROR", result.e.toString())
                }
            }
        }
        binding.viewModel = viewModel
        binding.test.image.setOnClickListener {
            findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(binding.test.photo!!))
        }
        if (viewModel.data.value is RandomRepository.DataState.Idle) viewModel.loadData()
    }
}
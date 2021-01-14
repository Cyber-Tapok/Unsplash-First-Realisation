package com.tapok.unsplash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapok.unsplash.CollectionsAdapter
import com.tapok.unsplash.MainGraphDirections
import com.tapok.unsplash.databinding.FragmentMainBinding
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.utils.PreCachingLayoutManager
import com.tapok.unsplash.utils.getDisplayWidth
import com.tapok.unsplash.utils.getMessageId
import com.tapok.unsplash.viewmodel.CollectionsViewModel
import com.tapok.unsplash.viewmodel.RandomViewModel
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private val collectionsAdapter = CollectionsAdapter()
    private val viewModel: RandomViewModel by viewModels()
    private val collectionsViewModel: CollectionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editText.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionNavMainFragmentToNavSearchResultsFragment())
        }
        bindRecyclerView()
        initObservers()
        binding.apply {
            randomPhoto.layoutLoadPhoto.image.setOnClickListener {
                findNavController().navigate(MainGraphDirections.actionGlobalDetailPhotoFragment(binding.randomPhoto.layoutLoadPhoto.photo!!))
            }
        }
        if (viewModel.data.value is DataState.Idle) viewModel.loadData()
        bindRefreshLayout()
    }

    private fun initObservers() {
        viewModel.data.observe(viewLifecycleOwner) { result ->
            binding.apply {
                randomPhoto.layoutLoadPhoto.layout.isVisible = result !is DataState.Idle && result !is DataState.Failed
                randomPhoto.layoutError.layout.isVisible = result is DataState.Failed
                collectionsLayout.isVisible = result !is DataState.Failed
                searchLayout.isVisible = result !is DataState.Failed

            }
            when (result) {
                is DataState.Success -> {
                    binding.randomPhoto.layoutLoadPhoto.photo = result.data
                }
                is DataState.Failed -> {
                    binding.randomPhoto.layoutError.textError.text =
                        getString(result.e.getMessageId())
                }
                else -> {
                }
            }
        }
        collectionsViewModel.data.observe(viewLifecycleOwner) { result ->
            collectionsAdapter.submitData(viewLifecycleOwner.lifecycle, result)
        }
    }

    private fun bindRefreshLayout() {
        binding.swipeContainer.apply {
            setOnRefreshListener {
                isRefreshing = true
                collectionsViewModel.refresh()
                viewModel.loadData()
                isRefreshing = false
            }
        }
    }

    private fun bindRecyclerView() {
        collectionsAdapter.clickListener = { collection ->
            findNavController().navigate(
                MainFragmentDirections.actionNavMainFragmentToNavCollectionsFragment(
                    collection
                )
            )
        }
        binding.collectionList.apply {
            setHasFixedSize(true)
            layoutManager =
                PreCachingLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            (layoutManager as PreCachingLayoutManager).initialPrefetchItemCount = 5
            (layoutManager as PreCachingLayoutManager).setExtraLayoutSpace(getDisplayWidth())
            adapter = collectionsAdapter
        }
    }

}
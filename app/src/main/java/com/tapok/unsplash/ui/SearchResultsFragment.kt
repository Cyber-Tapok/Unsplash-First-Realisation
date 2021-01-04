package com.tapok.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tapok.unsplash.databinding.FragmentSearchResultsBinding

class SearchResultsFragment : Fragment() {
    private lateinit var binding: FragmentSearchResultsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchResultsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
package com.tapok.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tapok.unsplash.databinding.FragmentCollectionsBinding

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCollectionsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
package com.tapok.unsplash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tapok.unsplash.databinding.FragmentDetailPhotoBinding

class DetailPhotoFragment : Fragment() {
    private lateinit var binding: FragmentDetailPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}
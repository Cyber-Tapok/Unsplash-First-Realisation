package com.tapok.unsplash.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tapok.unsplash.databinding.FragmentDetailPhotoBinding
import com.tapok.unsplash.model.UnsplashPhoto


class DetailPhotoFragment : Fragment() {
    private lateinit var binding: FragmentDetailPhotoBinding
    private lateinit var photo: UnsplashPhoto

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailPhotoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photo = getPhoto()
        binding.apply {
            photo = photo
            fullPhoto.setOnClickListener {
                openFullPhoto(photo.urls.full)
            }
        }
    }

    private fun getPhoto() = DetailPhotoFragmentArgs.fromBundle(requireArguments()).photo

    private fun openFullPhoto(url: String) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
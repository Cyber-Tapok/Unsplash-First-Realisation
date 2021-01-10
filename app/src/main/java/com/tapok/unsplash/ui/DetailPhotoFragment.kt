package com.tapok.unsplash.ui

import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.tapok.unsplash.databinding.FragmentDetailPhotoBinding
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.retrofit.DataState
import com.tapok.unsplash.viewmodel.RandomViewModel


class DetailPhotoFragment : Fragment() {
    private lateinit var binding: FragmentDetailPhotoBinding

//    private val viewModel: RandomViewModel by viewModels()
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
        Log.e("aa", savedInstanceState.toString())
        photo = getPhoto()
        binding.photo = photo
        binding.fullPhoto.setOnClickListener {
            openFullPhoto(photo.urls.full)
        }
//        binding.photo = (viewModel.data.value as DataState.Success).data
    }

    private fun getPhoto() = DetailPhotoFragmentArgs.fromBundle(requireArguments()).photo

    private fun openFullPhoto(url: String) {
        val uri: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    }
}
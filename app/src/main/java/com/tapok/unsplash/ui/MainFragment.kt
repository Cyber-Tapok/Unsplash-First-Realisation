package com.tapok.unsplash.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.tapok.unsplash.databinding.FragmentMainBinding
import com.tapok.unsplash.model.UnsplashPhoto
import com.tapok.unsplash.retrofit.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding

    private var v: MutableLiveData<UnsplashPhoto> = MutableLiveData()

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
        v.observe(viewLifecycleOwner) {
            Log.e("unsplash", it.toString())
            binding.swipeContainer.isRefreshing = false
            binding.test.photo = v.value
        }
        binding.swipeContainer.setOnRefreshListener {
            sendReq()
        }
        savedInstanceState ?: sendReq()
    }

    private fun sendReq() {
        try {
            GlobalScope.launch(Dispatchers.IO) {
                v.postValue(RetrofitClient.unsplashService().getRandomImage())
            }
        } catch (e: Exception) {
            Log.e("ERROR", e.toString())
        }
    }
}
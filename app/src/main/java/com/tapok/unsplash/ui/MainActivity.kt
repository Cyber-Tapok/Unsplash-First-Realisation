package com.tapok.unsplash.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tapok.unsplash.R
import com.tapok.unsplash.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Unsplash)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
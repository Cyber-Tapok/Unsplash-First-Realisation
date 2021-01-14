package com.tapok.unsplash.utils

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment

fun View.hideKeyboard(){
    val inputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun calculateRatio(width: Int, height: Int) = String.format("%d:%d", width, height)

fun Fragment.getDisplayWidth() = resources.displayMetrics.widthPixels * 2
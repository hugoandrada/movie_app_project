package com.hugoandrada.movieapp.utils

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }
    fun View.gone() {
        visibility = View.GONE
    }

    fun Fragment.toast(
        message: String,
        duration: Int = Toast.LENGTH_SHORT) {

        Toast.makeText(
            requireContext(),
            message,
            duration
        ).show()
    }
}
package com.hugoandrada.movieapp.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

object Extensions {

    fun View.show() {
        visibility = View.VISIBLE
    }
    fun View.gone() {
        visibility = View.GONE
    }

    fun Fragment.toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), message, duration).show()
    }

    fun ImageView.loadImage(url: String) {
        if(url.isNotEmpty()) {
            Glide.with(this.context).load(url).fitCenter().into(this)
        }
    }
}
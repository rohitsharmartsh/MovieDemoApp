package com.example.myapplication.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingUtil {
    @JvmStatic
    @BindingAdapter("instantSearchMovieImage")
    fun instantSearchImage(view: ImageView, imageUrl: String) {
        try {
            Glide.with(view.context)
                .load(imageUrl)
                .into(view)
        } catch (e: Exception) {
        }
    }
}
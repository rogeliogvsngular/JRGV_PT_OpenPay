package com.sngular.rgarciavital.extensions

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

@BindingAdapter("loadimageFromUrl")
fun bindImageFromUrl(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val poster = "https://image.tmdb.org/t/p/w500" + imageUrl
        Glide.with(view.context)
            .load(poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}

@BindingAdapter("loadimageFromUrlExternal")
fun bindImageFromUrlExternal(view: ImageView, imageUrl: String?) {
    if (!imageUrl.isNullOrEmpty()) {
        val poster =  imageUrl.substring(1)
        Glide.with(view.context)
            .load(poster)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(view)
    }
}


@BindingAdapter("userRatingFormat")
fun bindRating(view: TextView, rating: Double?) {
    rating?.let {
        view.text = rating.toString()
    }
}



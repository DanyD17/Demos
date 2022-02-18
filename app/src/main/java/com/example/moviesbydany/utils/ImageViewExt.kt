package com.example.moviesbydany.utils


import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("setImage")
fun AppCompatImageView.setImage(path: String?) {
    Picasso.get()
        .load(path)
        .into(this)
}
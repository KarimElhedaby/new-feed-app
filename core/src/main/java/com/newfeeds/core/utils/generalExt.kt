package com.newfeeds.core.utils

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.newfeeds.core.R
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*


//Gson Converting

inline fun <reified R> String.fromJson() : R {
    return Gson().fromJson(this, R::class.java)
}

inline fun <reified R> R.toJson() : String {
    return Gson().toJson(this, R::class.java)
}

//Load image
fun ImageView?.loadImage(url: String?) {
    this?.context?.let {
        Glide.with(it)
            .load(url)
            .into(this)
    }
}
fun ImageView?.loadImage(uri: Uri?) {
    this?.context?.let {
        Glide.with(it)
            .load(uri)
            .into(this)
    }
}

fun <T> ImageView.loadImage(
    imageUrl: T?,
    placeholderDrawable: Drawable? = null,
    errorDrawable: Int? = null,
    fallbackDrawable: Drawable? = null,
    skipMemoryCache: Boolean = false,
    skipDiskCache: Boolean = false,
    isCircularImage: Boolean = false
) {

    val imageLoader = Glide.with(context)
        .load(imageUrl)
        .placeholder(placeholderDrawable)
        .error(errorDrawable)
        .fallback(fallbackDrawable)

    if (isCircularImage) imageLoader.apply(RequestOptions().circleCrop())

    if (skipDiskCache) imageLoader.diskCacheStrategy(DiskCacheStrategy.NONE)

    if (skipMemoryCache) imageLoader.skipMemoryCache(true)

    imageLoader.into(this)
}


// constraint group click listener
fun androidx.constraintlayout.widget.Group.setAllOnClickListener(listener: View.OnClickListener?) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}

//view

fun View.secretB() {
    if (!this.isGoneB()) {
        this.visibility = View.GONE
    }
}


fun View.showB() {
    if (!this.isVisibleB()) {
        this.visibility = View.VISIBLE
    }
}
fun View.isVisibleB(): Boolean = visibility == View.VISIBLE

fun View.isGoneB(): Boolean = visibility == View.GONE


fun Context.openLink(url: String?) {
    try {
        val webpage: Uri = Uri.parse(url)
        val webpageIntent = Intent(Intent.ACTION_VIEW, webpage)
        startActivity(webpageIntent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(
            this,
            getString(R.string.open_link_error_android),
            Toast.LENGTH_LONG
        ).show()
    }
}
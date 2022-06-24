package com.example.newfeedsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// that's the final model will work with in presentation layer
@Parcelize
data class NewFeed(
    val author: String="",
    val description: String="",
    val publishedAt: String="",
    val title: String="",
    val url: String="",
    val image: String=""
    ): Parcelable




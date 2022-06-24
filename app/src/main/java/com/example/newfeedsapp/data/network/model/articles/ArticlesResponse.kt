package com.example.newfeedsapp.data.network.model.articles

data class ArticlesResponse(
    val articles: MutableList<Article>,
    val sortBy: String,
    val source: String,
    val status: String
)
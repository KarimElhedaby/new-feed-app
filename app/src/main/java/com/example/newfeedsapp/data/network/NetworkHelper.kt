package com.example.newfeedsapp.data.network

import com.example.newfeedsapp.data.network.model.articles.ArticlesResponse
import io.reactivex.rxjava3.core.Observable

interface NetworkHelper {

    fun getTheNextWebArticles(): Observable<ArticlesResponse>

    fun getAssociatedPressArticles(): Observable<ArticlesResponse>
}
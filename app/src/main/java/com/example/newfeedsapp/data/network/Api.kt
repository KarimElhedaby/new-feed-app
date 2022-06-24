package com.example.newfeedsapp.data.network

import com.example.newfeedsapp.data.network.model.articles.ArticlesResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface Api {

    @GET("articles?source=the-next-web")
    fun getTheNextWebArticles(): Observable<ArticlesResponse>

    @GET("articles?source=associated-press")
    fun getAssociatedPressArticles(): Observable<ArticlesResponse>
}
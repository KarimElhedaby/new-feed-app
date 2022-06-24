package com.example.newfeedsapp.data.network

import com.example.newfeedsapp.data.network.model.articles.ArticlesResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AppNetworkHelper @Inject constructor(private val api: Api) : NetworkHelper {

    override fun getTheNextWebArticles(): Observable<ArticlesResponse> {
        return api.getTheNextWebArticles()
    }

    override fun getAssociatedPressArticles(): Observable<ArticlesResponse> {
        return api.getAssociatedPressArticles()
    }
}
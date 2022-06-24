package com.example.newfeedsapp.data.repositories

import com.example.newfeedsapp.data.network.NetworkHelper
import com.example.newfeedsapp.domain.mapper.ArticleListMapper
import com.example.newfeedsapp.domain.repositories.Repository
import com.example.newfeedsapp.domain.model.NewFeed
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val networkHelper: NetworkHelper,
    private val mapper: ArticleListMapper) : Repository {

    // map api's response to mapping models
    override fun getTheNextWebArticles(): Observable<MutableList<NewFeed>> {
        return networkHelper.getTheNextWebArticles().map { mapper.map(it) }
    }

    override fun getAssociatedPressArticles(): Observable<MutableList<NewFeed>> {
        return networkHelper.getAssociatedPressArticles().map { mapper.map(it) }
    }
}
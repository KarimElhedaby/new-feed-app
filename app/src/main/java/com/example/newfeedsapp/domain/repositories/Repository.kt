package com.example.newfeedsapp.domain.repositories

import com.example.newfeedsapp.domain.model.NewFeed
import io.reactivex.rxjava3.core.Observable

interface Repository {

    fun getTheNextWebArticles(): Observable<MutableList<NewFeed>>

    fun getAssociatedPressArticles(): Observable<MutableList<NewFeed>>
}
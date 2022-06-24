package com.example.newfeedsapp.home.repository

import com.example.newfeedsapp.data.network.NetworkHelper
import com.example.newfeedsapp.data.network.model.articles.ArticlesResponse
import com.example.newfeedsapp.data.repositories.AppRepository
import com.example.newfeedsapp.domain.mapper.ArticleListMapper
import com.example.newfeedsapp.domain.model.NewFeed
import com.example.newfeedsapp.domain.repositories.Repository
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations


class FetchArticlesRepositoryTest {
    lateinit var repositoryImpl: Repository

    @Mock
    lateinit var networkHelper: NetworkHelper

    @Mock
    lateinit var mapper: ArticleListMapper


    @Before
    @Throws
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.openMocks(this)
        repositoryImpl = AppRepository(networkHelper, mapper)

    }

    @Test
    fun `get theNextWebArticles with mapping then return articles`() {
        val items = ArticlesResponse(articles = mutableListOf()  , sortBy = "" , source = "" , status = "" )
        val newFeeds = mutableListOf<NewFeed>()

        `when`(networkHelper.getTheNextWebArticles()).thenReturn(Observable.just(items))
        `when`(mapper.map(items)).thenReturn(newFeeds)

        repositoryImpl.getTheNextWebArticles().test().assertResult(newFeeds)

    }

    @Test
    fun `get associatedPressArticles with mapping then return flagships`() {
        val items = ArticlesResponse(articles = mutableListOf()  , sortBy = "" , source = "" , status = "" )
        val newFeeds = mutableListOf<NewFeed>()

        `when`(networkHelper.getAssociatedPressArticles()).thenReturn(Observable.just(items))
        `when`(mapper.map(items)).thenReturn(newFeeds)

        repositoryImpl.getAssociatedPressArticles().test().assertResult(newFeeds)

    }
}
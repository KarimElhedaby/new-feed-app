package com.example.newfeedsapp.domain.mapper

import com.example.newfeedsapp.data.network.model.articles.ArticlesResponse
import com.newfeeds.core.model.Mapper
import com.example.newfeedsapp.domain.model.NewFeed
import javax.inject.Inject

// map api articles response to map model "MutableList<NewFeed>>"

class ArticleListMapper @Inject constructor(var articleMapper: ArticleMapper) : Mapper<ArticlesResponse, MutableList<NewFeed>> {

    override fun map(input: ArticlesResponse?): MutableList<NewFeed> {
        return input?.articles?.map { articleMapper.map(it) }?.toMutableList() ?: mutableListOf()
    }
}
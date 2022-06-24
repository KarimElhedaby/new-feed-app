package com.example.newfeedsapp.domain.mapper

import com.example.newfeedsapp.data.network.model.articles.Article
import com.newfeeds.core.model.Mapper
import com.example.newfeedsapp.domain.model.NewFeed
import javax.inject.Inject

// map api Article response to map model "NewFeed"
class ArticleMapper @Inject constructor() : Mapper<Article, NewFeed> {
    override fun map(input: Article?): NewFeed {
        return NewFeed(
            author = input?.author ?: "",
            description = input?.description ?: "",
            publishedAt = input?.publishedAt ?: "",
            title = input?.title ?: "",
            url = input?.url ?: "",
            image = input?.urlToImage ?: ""
        )
    }

}

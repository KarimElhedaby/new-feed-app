package com.example.newfeedsapp.domain.usecases

import com.example.newfeedsapp.domain.model.NewFeed
import com.newfeeds.core.base.BaseUseCase
import com.newfeeds.core.model.Response
import com.example.newfeedsapp.domain.repositories.Repository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

/* By zipping two observables using the RxJava Zip operator,
both the network calls run in parallel. And we get the result
 of both the observables when both finish. In this way,
 we get the results of both the observables at a time.*/

// use case for fetch nextWebArticles  && associatedPressArticles useCase and return data to view model

class FetchNewFeedsUseCase @Inject constructor(private val repository: Repository) : BaseUseCase<Nothing, Observable<Response<MutableList<NewFeed>>>>() {
    override fun execute(input: Nothing?): Observable<Response<MutableList<NewFeed>>> {

        return repository.getTheNextWebArticles().zipWith(repository.getAssociatedPressArticles()) { nextWebArticles, associatedPressArticles ->
                nextWebArticles.apply { addAll(associatedPressArticles) } }.map { Response.Success(it) }
    }
}
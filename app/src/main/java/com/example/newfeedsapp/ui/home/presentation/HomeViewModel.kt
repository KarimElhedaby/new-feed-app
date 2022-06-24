package com.example.newfeedsapp.ui.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newfeedsapp.domain.model.NewFeed
import com.newfeeds.core.model.Response
import com.example.newfeedsapp.domain.usecases.FetchNewFeedsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: FetchNewFeedsUseCase
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    //progress loading
    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> get() = _loadingLiveData

    //error message
    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> get() = _errorMessageLiveData

    //observe newFeeds
    private val _newFeedsLiveData = MutableLiveData<MutableList<NewFeed>>()
    val newFeedsLiveData: LiveData<MutableList<NewFeed>> get() = _newFeedsLiveData

    fun getNewFeeds() {
        _loadingLiveData.postValue(true)
        compositeDisposable.add(
            useCase.execute(null)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        when (it) {
                            is Response.Error -> _errorMessageLiveData.postValue(it.errorMessage)

                            is Response.Success -> {
                                _loadingLiveData.postValue(false)
                                it.data.let { _newFeedsLiveData.postValue(it) }
                            }
                        }
                    }, onError = {
                        _errorMessageLiveData.postValue(it.localizedMessage ?: "")
                    }
                )
        )
    }


    /*
      ui elements can subscribe to observables and decide when to listen to its changes
       and when not to. Whenever the ui element gets removed from the content view,
       it’s important to dispose all of its subscriptions, otherwise they will cause a memory leak.*/
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}
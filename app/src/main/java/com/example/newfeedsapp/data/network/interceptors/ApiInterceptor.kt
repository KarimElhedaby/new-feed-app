package com.example.newfeedsapp.data.network.interceptors

import com.example.newfeedsapp.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


class ApiInterceptor @Inject constructor():Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        // api key  set on gradle.properties
        val url = request.url.newBuilder().addQueryParameter(API_KEY, BuildConfig.API_KEY).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object{
        private const val API_KEY="apiKey"
    }
}
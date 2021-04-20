package br.com.raywenderlich.jikan.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class GenericInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d("Request", chain.request().method().toString())
        Log.d("Request", chain.request().isHttps.toString())
        Log.d("Request", chain.request().headers().toString())

        return chain.proceed(chain.request())
    }
}
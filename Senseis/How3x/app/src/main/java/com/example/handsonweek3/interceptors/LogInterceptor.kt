package com.example.handsonweek3.interceptors

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

class LogInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.d(LOG_TAG, "HTTP")
        Log.d(LOG_TAG, "${chain.request().method()} -> ${chain.request().url()}")

        if (chain.request().headers().size() > 0) {
            Log.d(LOG_TAG, chain.request().headers().toString())
        }

        if (chain.request().body().toString().isNullOrEmpty()) {
            Log.d(LOG_TAG, chain.request().body().toString())
        }

        return chain.proceed(chain.request())
    }

    companion object {
        private const val LOG_TAG = "HandsOn (Week 3)"
    }
}
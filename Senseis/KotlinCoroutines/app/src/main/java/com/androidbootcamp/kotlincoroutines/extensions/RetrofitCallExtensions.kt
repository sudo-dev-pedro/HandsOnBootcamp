package com.androidbootcamp.kotlincoroutines.extensions

import com.androidbootcamp.kotlincoroutines.handlers.RequestResult
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultError
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultNotFound
import com.androidbootcamp.kotlincoroutines.handlers.RequestResultSuccess
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse

// Extension method to handle errors and avoid exceptions with custom types.
fun <T> Response<T>.toRequestResult(): RequestResult<T> {
    return if (isSuccessful) {
        // If the request runs with success, his data is returned.
        RequestResultSuccess(body()!!)
    } else {
        if (code() == 404) {
            // If the request returns 404 status, a custom not found type is returned.
            RequestResultNotFound()
        } else {
            // And if the request returns an unexpected error, a generic error type is returned.
            RequestResultError(code().toString() + ": " + message())
        }
    }
}
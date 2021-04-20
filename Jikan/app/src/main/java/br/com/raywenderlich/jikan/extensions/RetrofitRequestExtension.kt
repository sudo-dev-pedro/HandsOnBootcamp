package br.com.raywenderlich.jikan.extensions

import br.com.raywenderlich.jikan.handlers.RequestResult
import br.com.raywenderlich.jikan.handlers.RequestResultError
import br.com.raywenderlich.jikan.handlers.RequestResultNotFound
import br.com.raywenderlich.jikan.handlers.RequestResultSuccess
import retrofit2.Call
import retrofit2.awaitResponse

suspend fun <T> Call<T>.toRequestResult(): RequestResult<T> {
    val response = this.awaitResponse()

    return if (response.isSuccessful) {
        RequestResultSuccess(response.body()!!)
    } else {
        if (response.code() == 404) {
            RequestResultNotFound()
        } else {
            RequestResultError(response.code().toString() + ": " + response.message())
        }
    }
}
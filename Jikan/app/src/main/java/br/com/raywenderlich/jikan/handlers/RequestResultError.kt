package br.com.raywenderlich.jikan.handlers

open class RequestResultError<T>(
    val errorMessage: String
) : RequestResult<T>()
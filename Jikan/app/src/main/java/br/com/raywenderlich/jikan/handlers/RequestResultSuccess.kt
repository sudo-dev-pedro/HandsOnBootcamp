package br.com.raywenderlich.jikan.handlers

class RequestResultSuccess<T>(
    val data: T
) : RequestResult<T>()
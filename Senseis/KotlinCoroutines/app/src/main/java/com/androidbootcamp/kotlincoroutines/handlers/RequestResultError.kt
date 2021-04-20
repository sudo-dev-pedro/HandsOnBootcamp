package com.androidbootcamp.kotlincoroutines.handlers

open class RequestResultError<T>(val message: String) : RequestResult<T>()
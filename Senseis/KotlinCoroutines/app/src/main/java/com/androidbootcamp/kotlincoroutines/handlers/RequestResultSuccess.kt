package com.androidbootcamp.kotlincoroutines.handlers

data class RequestResultSuccess<T>(val data: T) : RequestResult<T>()
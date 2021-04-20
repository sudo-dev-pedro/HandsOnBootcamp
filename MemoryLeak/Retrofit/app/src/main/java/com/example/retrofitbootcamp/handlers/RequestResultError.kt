package com.example.retrofitbootcamp.handlers

open class RequestResultError<T>(val message: String) : RequestResult<T>()
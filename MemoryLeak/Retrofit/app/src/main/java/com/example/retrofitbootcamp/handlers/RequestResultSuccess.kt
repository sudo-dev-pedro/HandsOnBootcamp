package com.example.retrofitbootcamp.handlers

data class RequestResultSuccess<T>(val data: T) : RequestResult<T>()
package com.example.handsonweek3.handlers

data class RequestResultSuccess<T>(val data: T) : RequestResult<T>()
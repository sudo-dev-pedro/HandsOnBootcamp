package com.example.handsonweek3.handlers

open class RequestResultError<T>(val message: String) : RequestResult<T>()
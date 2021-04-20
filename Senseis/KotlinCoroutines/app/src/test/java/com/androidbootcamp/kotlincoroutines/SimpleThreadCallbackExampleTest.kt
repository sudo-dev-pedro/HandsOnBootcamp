package com.androidbootcamp.kotlincoroutines

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class SimpleThreadCallbackExampleTest {
    @Test
    fun simpleCallback() {
        val service = MyFakeNetworkService()

        service.search("foo", object : MyNetworkCallback {
            override fun onResult(result: String) {
                assertEquals("Search results for foo", result)
                println("result: $result")
            }

            override fun onFailure(exception: Exception) {
                throw Exception("should never be called")
            }
        })
    }

    @Test
    fun coroutineSuspendFun() = runBlocking {
        val service = MyFakeNetworkService()
        val coroutineService = MyFakeCoroutineNetworkService(service)

        val result = coroutineService.search("foo")

        assertEquals("Search results for foo", result)
    }

    class MyFakeNetworkService {
        fun search(term: String, callback: MyNetworkCallback) {
            Thread {
                val result = "Search results for $term"
                callback.onResult(result)
            }.also {
                it.start()
            }
        }
    }

    class MyFakeCoroutineNetworkService(
        private val service: MyFakeNetworkService
    ) {
        @OptIn(ExperimentalCoroutinesApi::class)
        suspend fun search(term: String) = suspendCancellableCoroutine<String> { continuation ->
            service.search(term, object : MyNetworkCallback {
                override fun onResult(result: String) {
                    continuation.resume(result)
                }

                override fun onFailure(exception: Exception) {
                    continuation.resumeWithException(exception)
                }
            })
        }
    }

    interface MyNetworkCallback {
        fun onResult(result: String)
        fun onFailure(exception: Exception)
    }
}

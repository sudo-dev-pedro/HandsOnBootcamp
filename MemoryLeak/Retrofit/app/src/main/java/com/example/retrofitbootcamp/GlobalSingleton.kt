package com.example.retrofitbootcamp

import android.util.Log

object GlobalSingleton {

    private val listeners = mutableListOf<GlobalSingletonListener>()

    fun register(listener: GlobalSingletonListener) {
        listeners.add(listener)
    }

    fun unregister(listener: GlobalSingletonListener) {
        listeners.remove(listener)
    }

    fun triggerAllListenersEvent() {
        if (listeners.isEmpty()) {
            Log.d(this.toString(), "Hi -> No listeners registered")
            return
        }

        listeners.forEach {
            it.onEvent()
        }
    }
}
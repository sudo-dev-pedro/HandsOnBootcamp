package com.example.testapplication

class Door(val type: DoorType, private var windowState: WindowState = WindowState.UP) {
    fun windowState(): WindowState {
        return windowState
    }

    fun openWindow() {
        windowState = WindowState.DOWN
    }

    fun closeWindow() {
        windowState = WindowState.UP
    }
}
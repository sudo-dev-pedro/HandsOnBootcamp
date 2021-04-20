package com.example.banksystem.extensions

import android.text.Editable
import java.lang.NumberFormatException

fun Editable?.toFloat(): Float {
    if (this == null) throw NumberFormatException()

    return this.toString().toFloat()
}
package com.example.banksystem.extensions

import android.text.Editable

fun Editable?.toFloat(): Float{
    if (this == null) return 0F

    return this.toString().toFloat()
}
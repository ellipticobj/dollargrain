package com.luna.dollargrain.util

import java.util.*

fun String.titleCase():String = this.replaceFirstChar {
    if (!it.isLowerCase()) {
        it.lowercase(Locale.getDefault())
    } else {
        it.toString()
    }
}
package com.example.extensions

import com.example.extensions.RegexUtils.ALPHANUMERIC_REGEX

fun String.cleanNullChars(): String {
    return this.replace("\u0000", "")

}

fun String.containsOnlyAlphanumeric(): Boolean {
    return !ALPHANUMERIC_REGEX.containsMatchIn(this)
}

object RegexUtils {
    val ALPHANUMERIC_REGEX = Regex("[^a-zA-Z0-9]")
}
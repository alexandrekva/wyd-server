package com.example.extensions

fun Byte.toHex(): String {
    val hexDigits = CharArray(2)
    hexDigits[0] = Character.forDigit((this.toInt() shr 4) and 0xF, 16)
    hexDigits[1] = Character.forDigit(this.toInt() and 0xF, 16)
    return String(hexDigits)
}
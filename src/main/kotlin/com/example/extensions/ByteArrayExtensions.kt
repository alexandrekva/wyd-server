package com.example.extensions

import java.nio.ByteBuffer

fun ByteArray.printBytes() {
    print("Buffer $this bytes: ")
    for (b in this) {
        print("$b ")
    }
    println()
}

fun ByteArray.printBytesHex() {
    print("Buffer $this bytes in hex: ")
    for (b in this) {
        print("${b.toHex()} ")
    }
    println()
}

fun ByteArray.completeHexString(): String {
    var string = ""
    for (b in this) {
        string += "${b.toHex()} "
    }
    return string
}
fun ByteArray.completeString(): String {
    var string = ""
    for (b in this) {
        string += "$b "
    }
    return string
}

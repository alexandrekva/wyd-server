package com.example.domain.packets.request

import com.example.domain.packets.PacketHeader
import com.example.extensions.completeHexString

data class RequestPacket(
    val header: PacketHeader, val content: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as RequestPacket

        if (header != other.header) return false
        return content.contentEquals(other.content)
    }

    override fun hashCode(): Int {
        var result = header.hashCode()
        result = 31 * result + content.contentHashCode()
        return result
    }

    override fun toString(): String {
        return "$header\n ${content.completeHexString()}"
    }

    fun print() {
        println(toString())
    }
}
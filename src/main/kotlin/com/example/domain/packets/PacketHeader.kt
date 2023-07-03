package com.example.domain.packets

import java.nio.ByteBuffer

data class PacketHeader(
    val size: UShort,
    val key: UByte,
    val hash: UByte = UByte.MIN_VALUE,
    val code: Short,
    val id: Short,
    val timestamp: UInt
) {
    override fun toString(): String {
        return "Size: $size \nKey: $key \nHash: $hash \nCode: $code \nId: $id \nTimestamp: $timestamp"
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(HEADER_SIZE)

        buffer.putShort(size.toShort())
        buffer.put(key.toByte())
        buffer.put(hash.toByte())
        buffer.putShort(code)
        buffer.putShort(id)
        buffer.putInt(timestamp.toInt())

        return buffer
    }

    companion object {
        const val SIZE = 0
        const val KEY = 2
        const val HASH_KEY = 3
        const val HEADER_SIZE = 12
    }
}
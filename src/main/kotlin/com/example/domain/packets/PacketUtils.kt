package com.example.domain.packets

import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.random.Random

object PacketUtils {
    fun generateKey(): UByte {
        val key = Random.nextInt(0, 256)
        return key.toUByte()
    }

    fun getTimestamp(): UInt {
        val timestamp = System.currentTimeMillis() / 1000
        return timestamp.toUInt()
    }

    fun buildByteBuffer(size: Int): ByteBuffer {
        return ByteBuffer.allocate(size).order(ByteOrder.LITTLE_ENDIAN)
    }

    fun buildByteBuffer(array: ByteArray): ByteBuffer {
        return ByteBuffer.wrap(array).order(ByteOrder.LITTLE_ENDIAN)
    }
}
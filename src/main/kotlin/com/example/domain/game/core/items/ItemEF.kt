package com.example.domain.game.core.items

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class ItemEF {
    var type: Byte = 0
    var value: Byte = 0

    constructor(buffer: ByteBuffer) {
        type = buffer.get(TYPE_BYTE)
        value = buffer.get(VALUE_BYTE)
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)
        buffer.put(type)
        buffer.put(value)

        return buffer
    }

    companion object {
        private const val SIZE = 2
        private const val TYPE_BYTE = 0
        private const val VALUE_BYTE = 1
    }
}
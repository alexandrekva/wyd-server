package com.example.domain.game.core.items

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class ItemEF {
    var type: Byte
    var value: Byte

    constructor(buffer: ByteBuffer) {
        type = buffer.get(TYPE_BYTE)
        value = buffer.get(VALUE_BYTE)
    }

    constructor(typeByte: Byte, valueByte: Byte) {
        type = typeByte
        value = valueByte
    }

    constructor() {
        type = 0
        value = 0
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
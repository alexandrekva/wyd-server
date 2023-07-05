package com.example.domain.game.core.items

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class ItemListEF {
    var index: Short
    var value: Short

    constructor(buffer: ByteBuffer) {
        index = buffer.getShort(INDEX_BYTES.first)
        value = buffer.getShort(VALUE_BYTES.first)
    }

    constructor(sh1: Short, sh2: Short) {
        index = sh1
        value = sh2
    }

    constructor() {
        index = 0
        value = 0
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.putShort(index)
        buffer.putShort(value)

        return buffer
    }

    companion object {
        private const val SIZE = 4
        private val INDEX_BYTES = 0..1
        private val VALUE_BYTES = 2..3
    }
}
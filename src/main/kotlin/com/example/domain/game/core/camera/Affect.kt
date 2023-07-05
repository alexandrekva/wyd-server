package com.example.domain.game.core.camera

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class Affect {
    var index: Byte
    var master: Byte
    var value: UShort
    var time: UInt

    constructor(buffer: ByteBuffer) {
        index = buffer.get(INDEX_BYTES)
        master = buffer.get(MASTER_BYTES)
        value = buffer.getShort(VALUE_BYTES.first).toUShort()
        time = buffer.getInt(TIME_BYTES.first).toUInt()
    }

    constructor() {
        index = 0
        master = 0
        value = 0u
        time = 0u
    }

    fun getBuffer() {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.put(index)
        buffer.put(master)
        buffer.putShort(value.toShort())
        buffer.putInt(time.toInt())
    }

    companion object {
        private const val SIZE = 8
        private const val INDEX_BYTES = 0
        private const val MASTER_BYTES = 1
        private val VALUE_BYTES = 2..3
        private val TIME_BYTES = 4..7
    }
}
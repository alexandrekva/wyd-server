package com.example.domain.game.core.charlist

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class CharListName {
    var nameBytes: ArrayList<Byte>

    constructor(name: String) {
        val nameArray = name.toByteArray()
        val nameByteArray = ArrayList<Byte>(16)
        nameByteArray.addAll(nameArray.toList())

        nameBytes = nameByteArray
    }

    constructor() {
        nameBytes = ArrayList<Byte>(16)
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)
        buffer.put(nameBytes.toByteArray())

        return buffer
    }

    companion object {
        private const val SIZE = 16
        private val NAME_BYTES = 0..15
    }
}
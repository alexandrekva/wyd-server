package com.example.domain.game.core.map

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class Position {
    var x: Short
    var y: Short

    constructor(buffer: ByteBuffer) {
        x = buffer.getShort(X_BYTES.first)
        y = buffer.getShort(Y_BYTES.first)
    }

    constructor() {
        x = 0
        y = 0
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.putShort(X_BYTES.first, x)
        buffer.putShort(Y_BYTES.first, y)
        return buffer
    }

    fun getDistance(other: Position): Int {
        val dx = this.x - other.x
        val dy = this.y - other.y

        return maxOf(dx, dy)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Position -> (this.x == other.x && this.y == other.y)
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = x
        result = (31 * result + y).toShort()
        return result.toInt()
    }

    companion object {
        private const val SIZE = 4
        private val X_BYTES = 0..1
        private val Y_BYTES = 2..3
    }
}
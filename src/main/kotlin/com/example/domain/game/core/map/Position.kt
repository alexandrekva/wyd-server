package com.example.domain.game.core.map

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class Position {
    var X: Short = 0
    var Y: Short = 0

    constructor(buffer: ByteBuffer) {
        X = buffer.getShort(X_BYTES.first)
        Y = buffer.getShort(Y_BYTES.first)
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.putShort(X_BYTES.first, X)
        buffer.putShort(Y_BYTES.first, Y)
        return buffer
    }

    fun getDistance(other: Position): Int {
        val dx = this.X - other.X
        val dy = this.Y - other.Y

        return maxOf(dx, dy)
    }

    override fun equals(other: Any?): Boolean {
        return when (other) {
            is Position -> (this.X == other.X && this.Y == other.Y)
            else -> false
        }
    }

    override fun hashCode(): Int {
        var result = X
        result = (31 * result + Y).toShort()
        return result.toInt()
    }

    companion object {
        private const val SIZE = 4
        private val X_BYTES = 0..1
        private val Y_BYTES = 2..3
    }
}
package com.example.domain.game.core.charlist

import com.example.domain.game.client.GameClient
import com.example.domain.game.core.mobs.Status
import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class CharList {
    var unk1: ByteArray

    var charPosX: ShortArray
    var charPosY: ShortArray

    var charNames: MutableList<CharListName>
    var charStatus: MutableList<Status>
    var charEquips: MutableList<CharListEquip>

    var unk2: ByteArray

    var charGolds: IntArray

    var charExps: ULongArray

    constructor(gameClient: GameClient) {
        unk1 = ByteArray(4)

        charPosX = ShortArray(4)
        charPosY = ShortArray(4)

        charNames = MutableList(4) { CharListName() }
        charStatus = MutableList(4) { Status() }
        charEquips = MutableList(4) { CharListEquip() }

        unk2 = ByteArray(8)

        charGolds = IntArray(4)
        charExps = ULongArray(4)
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.put(unk1)

        for (sh in charPosX) {
            buffer.putShort(sh)
        }

        for (sh in charPosY) {
            buffer.putShort(sh)
        }

        for (name in charNames) {
            val array = name.getBuffer().array()
            buffer.put(array)
        }

        for (status in charStatus) {
            val array = status.getBuffer().array()
            buffer.put(array)
        }

        for (equips in charEquips) {
            val array = equips.getBuffer().array()
            buffer.put(array)
        }

        buffer.put(unk2)

        for (i in charGolds) {
            buffer.putInt(i)
        }

        for (l in charExps) {
            buffer.putLong(l.toLong())
        }

        return buffer
    }

    companion object {
        private const val SIZE = 844
        private val UNK1_BYTES = 0..3
        private val POS_X_BYTES = 4..11
        private val POS_Y_BYTES = 12..19
        private val CHAR_NAMES_BYTES = 20..83
        private val CHAR_STATUS_BYTES = 84..275
        private val CHAR_EQUIPS_BYTES = 276..787
    }
}
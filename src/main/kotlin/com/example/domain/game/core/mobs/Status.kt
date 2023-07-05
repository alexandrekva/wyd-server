package com.example.domain.game.core.mobs

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class Status {
    var level: Int

    var defense: Int
    var attack: Int

    var merchant: Byte
    var speed: Byte
    var direction: Byte
    var chaosRate: Byte

    var maxHP: UInt
    var maxMP: UInt
    var currentHP: UInt
    var currentMP: UInt

    var str: Short
    var int: Short
    var dex: Short
    var con: Short

    var master: ShortArray

    constructor(buffer: ByteBuffer) {
        level = buffer.getInt(LEVEL_BYTES.first)

        defense = buffer.getInt(DEFENSE_BYTES.first)
        attack = buffer.getInt(ATTACK_BYTES.first)

        merchant = buffer.get(MERCHANT_BYTES)
        speed = buffer.get(SPEED_BYTES)
        direction = buffer.get(DIRECTION_BYTES)
        chaosRate = buffer.get(CHAOS_RATE_BYTES)

        maxHP = buffer.getInt(MAX_HP_BYTES.first).toUInt()
        maxMP = buffer.getInt(MAX_MP_BYTES.first).toUInt()
        currentHP = buffer.getInt(CURRENT_HP_BYTES.first).toUInt()
        currentMP = buffer.getInt(CURRENT_MP_BYTES.first).toUInt()

        str = buffer.getShort(STR_BYTES.first)
        int = buffer.getShort(INT_BYTES.first)
        dex = buffer.getShort(DEX_BYTES.first)
        con = buffer.getShort(CON_BYTES.first)

        val masterList = mutableListOf<Short>()

        for (i in MASTER_BYTES step 2) {
            masterList.add(buffer.getShort(i))
        }

        master = masterList.toShortArray()
    }

    constructor() {
        level = 0

        defense = 0
        attack = 0

        merchant = 0
        speed = 0
        direction = 0
        chaosRate = 0

        maxHP = 0u
        maxMP = 0u
        currentHP = 0u
        currentMP = 0u

        str = 0
        int = 0
        dex = 0
        con = 0

        master = shortArrayOf(0, 0, 0, 0)
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.putInt(level)

        buffer.putInt(defense)
        buffer.putInt(attack)

        buffer.put(merchant)
        buffer.put(speed)
        buffer.put(direction)
        buffer.put(chaosRate)

        buffer.putInt(maxHP.toInt())
        buffer.putInt(maxMP.toInt())
        buffer.putInt(currentHP.toInt())
        buffer.putInt(currentMP.toInt())

        buffer.putShort(str)
        buffer.putShort(int)
        buffer.putShort(dex)
        buffer.putShort(con)

        master.forEachIndexed { index, sh ->
            if (index < 4) buffer.putShort(sh)
        }

        return buffer
    }

    companion object {
        private const val SIZE = 48

        private val LEVEL_BYTES = 0..3

        private val DEFENSE_BYTES = 4..7
        private val ATTACK_BYTES = 8..11

        private const val MERCHANT_BYTES = 12
        private const val SPEED_BYTES = 13
        private const val DIRECTION_BYTES = 14
        private const val CHAOS_RATE_BYTES = 15

        private val MAX_HP_BYTES = 16..19
        private val MAX_MP_BYTES = 20..23
        private val CURRENT_HP_BYTES = 24..27
        private val CURRENT_MP_BYTES = 28..31

        private val STR_BYTES = 32..33
        private val INT_BYTES = 34..35
        private val DEX_BYTES = 36..37
        private val CON_BYTES = 38..39

        private val MASTER_BYTES = 40..47
    }
}
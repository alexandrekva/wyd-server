
package com.example.domain.game.core.items

import com.example.domain.packets.PacketUtils
import com.example.extensions.cleanNullChars
import java.nio.ByteBuffer

class ItemList {
    var nameBytes: ByteArray

    var mesh: Short
    var texture: Int

    var level: Short
    var str: Short
    var int: Short
    var dex: Short
    var con: Short

    var itemListEFArray: ArrayList<ItemListEF>

    var price: Int
    var unique: Short
    var slot: Short
    var extreme: Short
    var grade: Short

    constructor(buffer: ByteBuffer) {
        nameBytes = buffer.array().slice(NAME_BYTES).toByteArray()

        mesh = buffer.getShort(MESH_BYTES.first)
        texture = buffer.getInt(TEXTURE_BYTES.first)

        level = buffer.getShort(LEVEL_BYTES.first)
        str = buffer.getShort(STR_BYTES.first)
        int = buffer.getShort(INT_BYTES.first)
        dex = buffer.getShort(DEX_BYTES.first)
        con = buffer.getShort(CON_BYTES.first)

        val arrayList = arrayListOf<ItemListEF>()

        for (i in ITEM_LIST_EF_BYTES step 4) {
            val itemListEF = ItemListEF(buffer.getShort(i), buffer.getShort(i + 2))
            arrayList.add(itemListEF)
        }

        itemListEFArray = arrayList

        price = buffer.getInt(PRICE_BYTES.first)
        unique = buffer.getShort(UNIQUE_BYTES.first)
        slot = buffer.getShort(SLOT_BYTES.first)
        extreme = buffer.getShort(EXTREME_BYTES.first)
        grade = buffer.getShort(GRADE_BYTES.first)
    }

    fun getName(): String {
       return nameBytes.decodeToString().cleanNullChars()
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.put(nameBytes)

        buffer.putShort(mesh)
        buffer.putInt(texture)

        buffer.putShort(level)
        buffer.putShort(str)
        buffer.putShort(int)
        buffer.putShort(dex)
        buffer.putShort(con)

        itemListEFArray.forEachIndexed { index, itemListEF ->
            if (index < 12) {
                buffer.put(itemListEF.getBuffer().array())
            }
        }

        buffer.putInt(price)
        buffer.putShort(unique)
        buffer.putShort(slot)
        buffer.putShort(extreme)
        buffer.putShort(grade)

        return buffer
    }

    companion object {
        private const val SIZE = 140
        private val NAME_BYTES = 0..63
        private val MESH_BYTES = 64..65
        private val TEXTURE_BYTES = 66..69
        private val LEVEL_BYTES = 70..71
        private val STR_BYTES = 72..73
        private val INT_BYTES = 74..75
        private val DEX_BYTES = 76..77
        private val CON_BYTES = 78..79
        private val ITEM_LIST_EF_BYTES = 80..127
        private val PRICE_BYTES = 128..131
        private val UNIQUE_BYTES = 132..133
        private val SLOT_BYTES = 134..135
        private val EXTREME_BYTES = 136..137
        private val GRADE_BYTES = 138..139
    }
}
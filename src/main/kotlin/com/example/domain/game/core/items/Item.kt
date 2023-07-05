package com.example.domain.game.core.items

import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class Item {
    var id: Short
    var itemEFArray: ArrayList<ItemEF>

    constructor() {
        id = 0
        itemEFArray = arrayListOf(ItemEF(), ItemEF(), ItemEF())
    }

    constructor(buffer: ByteBuffer) {
        id = buffer.getShort(ID_BYTES.first)

        val arrayList = arrayListOf<ItemEF>()

        for (i in ITEM_EF_BYTES step 2) {
            val itemEF = ItemEF(buffer.get(i), buffer.get(i + 1))
            arrayList.add(itemEF)
        }

        itemEFArray = arrayList
    }

    fun getBuffer() {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        buffer.putShort(id)

        itemEFArray.forEachIndexed { index, itemEF ->
            if (index < 3) buffer.put(itemEF.getBuffer().array())
        }
    }

    companion object {
        private const val SIZE = 8
        private val ID_BYTES = 0..1
        private val ITEM_EF_BYTES = 2..7
    }
}
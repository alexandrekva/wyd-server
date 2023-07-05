package com.example.domain.game.core.charlist

import com.example.domain.game.core.items.Item
import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class CharListEquip {
    var itemList: ArrayList<Item>

    constructor() {
        val arrayList = arrayListOf<Item>()
        for (i in 0 until ITEM_LIST_SIZE) {
            arrayList.add(Item())
        }

        itemList = arrayList
    }

    constructor(buffer: ByteBuffer) {
        val arrayList = arrayListOf<Item>()

        for (i in ITEM_BYTES step 8) {
            val byteArray = buffer.array().sliceArray(i until i+8)
            val itemBuffer = PacketUtils.buildByteBuffer(byteArray)

            arrayList.add(Item(itemBuffer))
        }

        itemList = arrayList
    }

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(SIZE)

        for (i in itemList) {
            buffer.put(i.getBuffer().array())
        }

        return buffer
    }

    companion object {
        private const val SIZE = 128
        private const val ITEM_LIST_SIZE = 16
        private val ITEM_BYTES = 0..127
    }
}
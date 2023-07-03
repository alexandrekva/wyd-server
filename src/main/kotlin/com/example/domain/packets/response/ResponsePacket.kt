package com.example.domain.packets.response

import com.example.domain.packets.PacketDefinitions
import com.example.domain.packets.PacketHeader
import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

abstract class ResponsePacket(private val packet: PacketDefinitions, private val clientId: Int = 0) {

    fun getBuffer(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(packet.size)
        val headerByteArray = buildHeader().getBuffer().array()
        val contentByteArray = buildContent().array()

        buffer.put(headerByteArray)
        buffer.put(contentByteArray)

        return buffer
    }

    abstract fun buildContent(): ByteBuffer

    private fun buildHeader(): PacketHeader {
        val size = packet.size.toUShort()
        val key = PacketUtils.generateKey()
        val code = packet.code.toShort()
        val id = clientId.toShort()
        val timeStamp = PacketUtils.getTimestamp()

        return PacketHeader(size = size, key = key, code = code, id = id, timestamp = timeStamp)
    }
}
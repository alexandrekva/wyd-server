package com.example.domain.packets.response

import com.example.domain.packets.PacketDefinitions
import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class BannerMessageResponse(private val message: String): ResponsePacket(PacketDefinitions.BANNER_MESSAGE) {
    override fun buildContent(): ByteBuffer {
        val content = message.toByteArray(Charsets.US_ASCII)
        return PacketUtils.buildByteBuffer(content)
    }
}
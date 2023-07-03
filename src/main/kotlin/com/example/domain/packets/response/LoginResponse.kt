package com.example.domain.packets.response

import com.example.domain.packets.PacketDefinitions
import com.example.domain.packets.PacketUtils
import java.nio.ByteBuffer

class LoginResponse(private val clientId: Int) : ResponsePacket(PacketDefinitions.LOGIN, clientId) {
    override fun buildContent(): ByteBuffer {
        return PacketUtils.buildByteBuffer(1)
    }
}
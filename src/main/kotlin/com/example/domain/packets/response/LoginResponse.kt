package com.example.domain.packets.response

import com.example.domain.packets.PacketDefinitions
import com.example.domain.packets.PacketUtils
import com.example.extensions.printBytesHex
import java.nio.ByteBuffer

class LoginResponse(private val clientId: Int, private val userName: String) :
    ResponsePacket(PacketDefinitions.LOGIN, clientId) {
    override fun buildContent(): ByteBuffer {
        val buffer = PacketUtils.buildByteBuffer(1916)

        val nameArray = arrayListOf<Byte>(12)
        val nameBytes = userName.toByteArray()

        nameArray.addAll(nameBytes.toList())

        buffer.put(USER_NAME_BYTES.first - 12, nameArray.toByteArray())
        return buffer
    }

    companion object {
        private val USER_NAME_BYTES = 1900..1911
    }
}
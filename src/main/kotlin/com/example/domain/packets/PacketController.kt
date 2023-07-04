package com.example.domain.packets

import com.example.domain.game.client.GameClient
import com.example.domain.game.client.GameClientStatus
import com.example.domain.packets.request.LoginRequest
import com.example.extensions.cleanConnectionAndLoginPacket
import com.example.extensions.doNothing
import com.example.extensions.size
import java.nio.ByteBuffer

class PacketController(private val gameClient: GameClient) {

    fun receivePacket(buffer: ByteBuffer) {
        when (gameClient.clientStatus) {
            GameClientStatus.CONNECTION -> treatConnectionPacket(buffer)
            GameClientStatus.LOGIN -> treatLoginPacket(buffer)
            GameClientStatus.PASSWORD -> {}
            GameClientStatus.CHARACTERS -> {}
            GameClientStatus.GAME -> {}
        }
    }

    private fun treatConnectionPacket(buffer: ByteBuffer) {
        when (buffer.size()) {
            LOGIN_PACKET_SIZE -> {
                gameClient.clientStatus = GameClientStatus.LOGIN
                receivePacket(buffer)
            }

            LOGIN_AND_CONNECT_SIZE -> {
                gameClient.clientStatus = GameClientStatus.LOGIN
                receivePacket(buffer.cleanConnectionAndLoginPacket())
            }

            else -> doNothing()
        }
    }

    private fun treatLoginPacket(buffer: ByteBuffer) {
        LoginRequest(buffer = buffer, sendResponse = gameClient::sendResponse)
    }

    companion object {
        private const val LOGIN_PACKET_SIZE = 116
        private const val LOGIN_AND_CONNECT_SIZE = 120
    }
}
package com.example.domain.game

import com.example.domain.packets.ConnectionPackets
import com.example.domain.packets.PacketUtils
import com.example.domain.packets.response.BannerMessageResponse
import com.example.domain.packets.response.LoginResponse
import com.example.extensions.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.util.*

class GameClient(
    private val scope: CoroutineScope,
    private val socket: Socket,
    private val onGameClientLoggedIn: (GameClient) -> Unit,
    private val onGameClientLoggedOff: (GameClient) -> Unit,
) {

    val clientUUID: UUID = UUID.randomUUID()

    private val receiveChannel = socket.openReadChannel()
    private val sendChannel = socket.openWriteChannel(autoFlush = true)

    var clientStatus = GameClientStatus.CONNECTION

    init {
        println("Accepted client: $clientUUID")
        scope.launch { start()}
    }


    suspend fun start() {
        try {
            socketListener()
        } catch (e: Throwable) {
            println("Error: ${e.message} / Disconnecting client")
            socket.close()
            onGameClientLoggedOff(this)
        }
    }

    private suspend fun socketListener() {
        while (true) {
            receiveChannel.awaitContent()

            when (val packetSize = receiveChannel.availableForRead) {
                0 -> break
                else -> {
                    val buffer = PacketUtils.buildByteBuffer(packetSize)
                    receiveChannel.readAvailable(buffer)
                    packetFlow(buffer)
                }
            }
        }
    }

    private fun sendResponse(buffer: ByteBuffer) {
        scope.launch {
            buffer.encryptPacket()
            sendChannel.writeAvailable(buffer)
        }
    }

    private fun packetFlow(buffer: ByteBuffer) {
        when (clientStatus) {
            GameClientStatus.CONNECTION -> treatConnectionPacket(buffer)
            GameClientStatus.LOGIN -> {}
            GameClientStatus.PASSWORD -> {}
            GameClientStatus.CHARACTERS -> {}
            GameClientStatus.GAME -> {}
        }
    }

    private fun treatConnectionPacket(buffer: ByteBuffer) {
        when (buffer.size()) {
            ConnectionPackets.CONNECT.size -> Unit
            ConnectionPackets.LOGIN.size -> packetTreatment(buffer)
            ConnectionPackets.CONNECT_AND_LOGIN.size -> packetTreatment(buffer.cleanConnectionAndLoginPacket())
        }
    }


    private fun packetTreatment(buffer: ByteBuffer) {
        buffer.decryptPacket()
        val loginResponse = LoginResponse(3002)
        sendResponse(loginResponse.getBuffer())
        val bannerMessageResponse = BannerMessageResponse("Testando isso aqui")
        sendResponse(bannerMessageResponse.getBuffer())
    }
}
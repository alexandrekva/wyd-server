package com.example.domain.game.client

import com.example.domain.packets.PacketController
import com.example.domain.packets.PacketUtils
import com.example.domain.packets.response.ResponsePacket
import com.example.extensions.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*

class GameClient(
    private val scope: CoroutineScope,
    private val socket: Socket,
    val onGameClientLoggedIn: (GameClient) -> Unit,
    val onGameClientLoggedOff: (GameClient) -> Unit,
) {

    val clientUUID: UUID = UUID.randomUUID()
    var clientStatus = GameClientStatus.CONNECTION

    private val packetController = PacketController(this)
    private val receiveChannel = socket.openReadChannel()
    private val sendChannel = socket.openWriteChannel(autoFlush = true)

    init {
        println("Accepted client: $clientUUID")
        scope.launch { start()}
    }


    private suspend fun start() {
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
                    packetController.receivePacket(buffer)
                }
            }
        }
    }

    fun sendResponse(response: ResponsePacket) {
        val buffer = response.getBuffer()

        println(buffer.getPacketHeader().toString())
        buffer.printBytesHex()
        buffer.encryptPacket()

        scope.launch {
            sendChannel.writeAvailable(buffer)
        }
    }
}
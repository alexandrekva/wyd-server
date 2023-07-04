package com.example.domain.server

import com.example.domain.game.client.GameClient
import io.ktor.network.selector.*
import io.ktor.network.sockets.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class GameServer {
    private val selectorManager = SelectorManager(Dispatchers.IO)
    private val serverSocket = aSocket(selectorManager)
        .tcp()
        .bind(ServerConfigs.LOCAL_IP, ServerConfigs.GAME_SERVER_PORT)

    private var onGameClientLoggedIn: (GameClient) -> Unit = { }
    private var onGameClientLoggedOff: (GameClient) -> Unit = { }

    suspend fun start() {
        println("Server is listening at ${serverSocket.localAddress}")

        coroutineScope {
            while (true) {
                val socket = serverSocket.accept()
                GameClient(this, socket, onGameClientLoggedIn, onGameClientLoggedOff)
            }
        }
    }
}
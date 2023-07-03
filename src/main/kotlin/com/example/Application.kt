package com.example

import com.example.domain.game.GameClient
import com.example.domain.game.GameClientStatus
import com.example.domain.server.GameServer
import com.example.domain.server.WebServer
import kotlinx.coroutines.runBlocking

fun main() {
    Server().init()
}

class Server {
    private val clients = mutableListOf<GameClient>()

    private val server = WebServer(::getLoggedClients)
    private val gameServer = GameServer()

    fun init() {
        runBlocking {
            server.start()
            gameServer.start()
        }
    }

    private fun getLoggedClients(): String {
        return "${clients.filter { it.clientStatus != GameClientStatus.CONNECTION }.size}"
    }

    private fun onGameClientLoggedIn() {

    }

    private fun onGameClientLoggedOut() {

    }
}

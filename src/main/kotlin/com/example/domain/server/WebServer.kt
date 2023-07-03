package com.example.domain.server

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.coroutines.coroutineScope

class WebServer(
    private val getLoggedClients: () -> String
) {

    private val server = embeddedServer(
        Netty,
        port = ServerConfigs.SERVER_PORT,
        host = ServerConfigs.LOCAL_IP,
        module = { configureRouting(this) }
    )

    suspend fun start() {
        coroutineScope {
            server.start()
        }
    }

    private fun configureRouting(application: Application) {
        application.routing {
            get("/server") {
                print(this.context.request)
                call.respondText(getLoggedClients())
            }
        }
    }

}
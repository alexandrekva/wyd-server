package com.example.domain.packets.request

import com.example.domain.packets.response.BannerMessageResponse
import com.example.domain.packets.response.LoginResponse
import com.example.domain.packets.response.ResponsePacket
import com.example.extensions.cleanNullChars
import com.example.extensions.containsOnlyAlphanumeric
import com.example.extensions.getRequestPacket
import java.nio.ByteBuffer

class LoginRequest(buffer: ByteBuffer, sendResponse: (response: ResponsePacket) -> Unit) {
    private val requestPacket = buffer.getRequestPacket()

    init {
        val user = getUserString()
        val password = getPasswordString()

        if (!user.containsOnlyAlphanumeric()) {
            val bannerMessageResponse = BannerMessageResponse(INVALID_LOGIN_MSG)
            sendResponse(bannerMessageResponse)
        } else if (!password.containsOnlyAlphanumeric()) {
            val bannerMessageResponse = BannerMessageResponse(INVALID_PASSWORD_MSG)
            sendResponse(bannerMessageResponse)
        } else {
            val bannerMessageResponse = BannerMessageResponse("User: $user Senha: $password")
            sendResponse(bannerMessageResponse)
            val loginResponse = LoginResponse(30002, user)
            sendResponse(loginResponse)
        }
    }

    private fun getUserString(): String {
        val loginString = requestPacket.content.decodeToString(USER_INTERVAL.first, USER_INTERVAL.last)
        return loginString.cleanNullChars()
    }

    private fun getPasswordString(): String {
        val passwordString = requestPacket.content.decodeToString(PASSWORD_INTERVAL.first, PASSWORD_INTERVAL.last)
        return passwordString.cleanNullChars()
    }

    companion object {
        private val PASSWORD_INTERVAL = 16..26
        private val USER_INTERVAL = 0..12
        private const val INVALID_LOGIN_MSG = "Caracteres invalidos encontrados. Verifique seu ID."
        private const val INVALID_PASSWORD_MSG = "Caracteres invalidos encontrados. Verifique sua Senha."
    }
}
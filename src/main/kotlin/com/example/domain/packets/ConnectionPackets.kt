package com.example.domain.packets

enum class ConnectionPackets(val size: Int) {
    CONNECT(4), LOGIN(116), CONNECT_AND_LOGIN(120)
}
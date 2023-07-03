package com.example.domain.packets

enum class PacketDefinitions(val code: Int, val size: Int) {
    BANNER_MESSAGE(257, 108),
    LOGIN(266, 1928),
    CONNECT(525, 116)
}
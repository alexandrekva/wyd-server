package com.example.domain.game.core.mobs

import com.example.domain.game.core.camera.Affect
import com.example.domain.game.core.items.Item
import com.example.domain.game.core.map.Position
import com.example.domain.packets.PacketUtils

class Mob {
    var nameBytes: ArrayList<Byte>

    var capeInfo: Byte
    var merchant: Byte

    var guildIndex: Short
    var classInfo: Byte
    var affectInfo: Byte
    var questInfo: Short

    var gold: Int

    var unk1: ByteArray

    var exp: ULong

    var lastPosition: Position

    var baseStatus: Status
    var gameStatus: Status

    var equip: MutableList<Item>
    var inventory: MutableList<Item>
    var andarilho: MutableList<Item>

    var unk2: ByteArray

    var item547: Short
    var chaosPoint: Byte
    var currentKill: Byte
    var totalKill: Short

    var unk3: ByteArray

    var learn: ULong

    var statusPoint: Short
    var masterPoint: Short
    var skillPoint: Short

    var critical: Byte
    var saveMana: Byte

    var skillBar1: ByteArray

    var unk4: ByteArray

    var fireResist: Byte
    var iceResist: Byte
    var holyResist: Byte
    var thunderResist: Byte

    var unk5: ByteArray

    var magicIncrement: Short

    var unk6: ByteArray

    var clientId: Short
    var cityId: Short

    var skillBar2: ByteArray

    var unk7: ByteArray

    var hold: UInt

    var tabBytes: ByteArray

    var unk8: ByteArray

    var affects: MutableList<Affect>

    constructor() {
        nameBytes = ArrayList(16)

        capeInfo = 0
        merchant = 0

        guildIndex = 0
        classInfo = 0
        affectInfo = 0
        questInfo = 0

        gold = 0

        unk1 = ByteArray(4)

        exp = 0u

        lastPosition = Position()

        baseStatus = Status()
        gameStatus = Status()

        equip = MutableList(16) { Item() }
        inventory = MutableList(60) { Item() }
        andarilho = MutableList(2) { Item() }

        unk2 = ByteArray(8)

        item547 = 0
        chaosPoint = 0
        currentKill = 0
        totalKill = 0

        unk3 = ByteArray(2)

        learn = 0u

        statusPoint = 0
        masterPoint = 0
        skillPoint = 0

        critical = 0
        saveMana = 0

        skillBar1 = ByteArray(4) { 255.toByte() }

        unk4 = ByteArray(4)

        fireResist = 0
        iceResist = 0
        holyResist = 0
        thunderResist = 0

        unk5 = ByteArray(210)

        magicIncrement = 0

        unk6 = ByteArray(6)

        clientId = 0
        cityId = 0

        skillBar2 = ByteArray(16) { 255.toByte() }

        unk7 = byteArrayOf(204.toByte(), 204.toByte())

        hold = 0u

        tabBytes = ByteArray(26)

        unk8 = ByteArray(2)

        affects = MutableList(32) { Affect() }
    }

    private fun base(name: String) {
        nameBytes.addAll(name.toByteArray().toList())

        baseStatus.defense = 4
        baseStatus.speed = 6

        val initEquip = PacketUtils.buildByteBuffer(8)
        initEquip.putShort(0)
        initEquip.put(43)
        initEquip.put(0)
        initEquip.put(86)
        initEquip.put(1)
        initEquip.put(28)
        initEquip.put(0)

        equip[0] = Item(initEquip)

        item547 = 547

        chaosPoint = 150.toByte()

        val positionInit = PacketUtils.buildByteBuffer(4)
        positionInit.putShort(2100)
        positionInit.putShort(2100)

        lastPosition = Position(positionInit)

        gameStatus = baseStatus
    }

    companion object {
        private const val SIZE = 1336
    }
}
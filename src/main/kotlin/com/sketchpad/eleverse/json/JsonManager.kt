package com.sketchpad.eleverse.json

import com.google.gson.Gson
import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.json.player.PlayerInfo
import org.bukkit.entity.Player
import java.io.File
import java.io.FileWriter

object JsonManager {

    /**
     * Checks if info file for [player] exists
     */
    fun infoWritten(player: Player): Boolean {
        return File("${Eleverse.PLAYER_INFO_FOLDER}\\${player.uniqueId}.json").exists()
    }

    /**
     * Serializes the [info] and writes it to a json file specific to the [player].
     */
    fun writePlayerInfo(player: Player, info: PlayerInfo) {
        val infoFile = File("${Eleverse.PLAYER_INFO_FOLDER}\\${player.uniqueId}.json")
        infoFile.createNewFile()
        val writer = FileWriter(infoFile)
        writer.write(Gson().toJson(info))
        writer.flush()
        writer.close()
    }

    /**
     * @return a [PlayerInfo] that was deserialized from the [player]'s json file.
     */
    fun getPlayerInfo(player: Player): PlayerInfo {
        return Gson().fromJson(File("${Eleverse.PLAYER_INFO_FOLDER}\\${player.uniqueId}.json").readText(), PlayerInfo::class.java)
    }
}
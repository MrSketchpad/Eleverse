package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.json.JsonManager
import com.sketchpad.eleverse.json.player.PlayerInfo
import com.sketchpad.eleverse.player.ElPlayer
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinHandler: Listener {
    /**
     * Runs the required checks when a player joins
     */
    @EventHandler
    fun loadStats(e: PlayerJoinEvent) {
        if (!JsonManager.infoWritten(e.player)) {
            JsonManager.writePlayerInfo(e.player, PlayerInfo(hashMapOf()))
        }
        val elPlayer = ElPlayer(e.player)
        elPlayer.startClock()
        Eleverse.players.add(elPlayer)

        for (item in JsonManager.getPlayerInfo(e.player).inventory) {
            e.player.inventory.setItem(item.key, item.value.toItemStack())
        }
    }
}
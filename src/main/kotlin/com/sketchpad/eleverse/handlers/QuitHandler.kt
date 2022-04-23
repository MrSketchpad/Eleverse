package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.json.JsonManager
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent

class QuitHandler: Listener {
    @EventHandler
    fun saveInventory(e: PlayerQuitEvent) {
        val inventory = hashMapOf<Int, ElItem>()
        for (i in 0..54) {
            val item =  e.player.inventory.getItem(i)
            if (item!=null) {
                inventory[i] = ElItem.fromItemStack(item)
            }
        }
        val info = JsonManager.getPlayerInfo(e.player)
        info.inventory = inventory
        JsonManager.writePlayerInfo(e.player, info)
    }
}
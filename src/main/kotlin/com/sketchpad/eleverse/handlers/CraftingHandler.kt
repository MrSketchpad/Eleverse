package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.menus.constants.Menu
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent

class CraftingHandler: Listener {
    @EventHandler
    fun overrideVanilla(e: PlayerInteractEvent) {
        if (e.clickedBlock!=null && e.clickedBlock!!.type== Material.CRAFTING_TABLE) {
            e.isCancelled = true
            Menu.ONE_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.player))
        }
    }
}
package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.menus.GUIMenu
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.EventHandler

class GuiHandler: Listener {
    @EventHandler
    fun checkClick(e: InventoryClickEvent) {
        if (GUIMenu.menus.containsKey(e.view.title)) {
            GUIMenu.menus[e.view.title]!!.onClick(e)
        }
        if (e.currentItem != null && e.currentItem!!.type != Material.AIR) {
            val nbt = NBTItem(e.currentItem)
            if (nbt.hasKey("id")) {
                val item = Eleverse.guiItems[nbt.getString("id")]!!
                if (!item.takeable) e.isCancelled = true
                item.onClick(e)
            }
        }
    }
}
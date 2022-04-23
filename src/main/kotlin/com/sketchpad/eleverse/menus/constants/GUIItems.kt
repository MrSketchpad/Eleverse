package com.sketchpad.eleverse.menus.constants

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.menus.GUIItem
import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.utils.green
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

enum class GUIItems(val item: GUIItem) {
    MENU_GLASS(object: GUIItem(Material.GRAY_STAINED_GLASS_PANE, "MENU_GLASS") {
        override fun getName(p: ElPlayer): String {
            return " "
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf()
        }
        override fun onClick(e: InventoryClickEvent) {
            e.isCancelled = true
        }
    }),
    EMPTY(object: GUIItem(Material.STONE, "EMPTY") {
        override fun getName(p: ElPlayer): String {
            return " "
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf()
        }
    })
}
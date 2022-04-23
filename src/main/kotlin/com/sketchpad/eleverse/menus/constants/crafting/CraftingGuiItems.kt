package com.sketchpad.eleverse.menus.constants.crafting

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.menus.GUIItem
import com.sketchpad.eleverse.menus.GUIMenu
import com.sketchpad.eleverse.menus.constants.Menu
import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.utils.*
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryClickEvent

enum class CraftingGuiItems(val item: GUIItem) {
    ONE_STAR(object: GUIItem(Material.RED_STAINED_GLASS_PANE, "ONE_STAR") {
        override fun getName(p: ElPlayer): String {
            return red("☆ Crafting")
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf("Opens the one star crafting table.")
        }
        override fun onClick(e: InventoryClickEvent) {
            Menu.ONE_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.whoClicked as Player))
        }
    }),
    TWO_STAR(object: GUIItem(Material.ORANGE_STAINED_GLASS_PANE, "TWO_STAR") {
        override fun getName(p: ElPlayer): String {
            return gold("☆☆ Crafting")
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf("Opens the two star crafting table.")
        }
        override fun onClick(e: InventoryClickEvent) {
            Menu.TWO_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.whoClicked as Player))
        }
    }),
    THREE_STAR(object: GUIItem(Material.YELLOW_STAINED_GLASS_PANE, "THREE_STAR") {
        override fun getName(p: ElPlayer): String {
            return yellow("☆☆☆ Crafting")
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf("Opens the three star crafting table.")
        }
        override fun onClick(e: InventoryClickEvent) {
            Menu.THREE_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.whoClicked as Player))
        }
    }),
    FOUR_STAR(object: GUIItem(Material.GREEN_STAINED_GLASS_PANE, "FOUR_STAR") {
        override fun getName(p: ElPlayer): String {
            return green("☆☆☆☆ Crafting")
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf("Opens the four star crafting table.")
        }
        override fun onClick(e: InventoryClickEvent) {
            Menu.FOUR_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.whoClicked as Player))
        }
    }),
    FIVE_STAR(object: GUIItem(Material.LIGHT_BLUE_STAINED_GLASS_PANE, "FIVE_STAR") {
        override fun getName(p: ElPlayer): String {
            return blue("☆☆☆☆☆ Crafting")
        }
        override fun getDescription(p: ElPlayer): List<String> {
            return listOf("Opens the five star crafting table.")
        }
        override fun onClick(e: InventoryClickEvent) {
            Menu.FIVE_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(e.whoClicked as Player))
        }
    }),
}
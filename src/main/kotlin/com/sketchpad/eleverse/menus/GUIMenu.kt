package com.sketchpad.eleverse.menus

import com.sketchpad.eleverse.menus.constants.GUIItems
import com.sketchpad.eleverse.menus.constants.Menu
import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.utils.log
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

open class GUIMenu(
    val name: String,
    val size: Int,
    vararg items: Pair<Int, GUIItem>
) {
    val items = hashMapOf<Int, GUIItem>()
    init {
        for (item in items) {
            this.items[item.first] = item.second
        }
    }
    fun open(p: ElPlayer) {
        p.player.openInventory(toSpigotInventory(p))
    }
    fun toSpigotInventory(p: ElPlayer): Inventory {
        val inv = Bukkit.createInventory(null, 54, name)
        for (i in 0 until size) {
            if (items.containsKey(i)) {
                if (items[i]!! != GUIItems.EMPTY.item) {
                    inv.setItem(i, items[i]!!.toItemStack(p))
                }
            } else inv.setItem(i, GUIItems.MENU_GLASS.item.toItemStack(p))
        }
        return inv
    }
    open fun onClick(e: InventoryClickEvent) {}

    companion object {
        val menus: HashMap<String, GUIMenu> = hashMapOf()
    }
}
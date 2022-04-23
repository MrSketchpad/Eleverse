package com.sketchpad.eleverse.menus

import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.utils.formatForDescription
import com.sketchpad.eleverse.utils.gray
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

abstract class GUIItem(val material: Material, val id: String, val takeable: Boolean = false) {
    abstract fun getName(p: ElPlayer): String
    abstract fun getDescription(p: ElPlayer): List<String>
    open fun onClick(e: InventoryClickEvent) {}
    fun toItemStack(p: ElPlayer): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta

        val lore = mutableListOf<String>()
        for (line in getDescription(p)) {
            lore.add(gray(line))
        }
        meta.lore = lore

        meta.setDisplayName(gray(getName(p)))
        item.itemMeta = meta
        val nbt = NBTItem(item)
        nbt.setString("id", id)
        return nbt.item
    }
}
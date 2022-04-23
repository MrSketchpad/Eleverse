package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.items.ElItem
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.player.PlayerPickupItemEvent

class ConvertToElItemHandler: Listener {
    @EventHandler
    fun onPickup(e: EntityPickupItemEvent) {
        e.item.itemStack = ElItem.fromItemStack(e.item.itemStack).toItemStack()
    }
}
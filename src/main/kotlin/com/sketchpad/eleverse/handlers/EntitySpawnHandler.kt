package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.entity.ElCreature
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.CreatureSpawnEvent

class EntitySpawnHandler: Listener {
    @EventHandler
    fun convertToEleverse(e: CreatureSpawnEvent) {
        val health = e.entity.maxHealth*10
        if (e.entity.type!=EntityType.ARMOR_STAND) {
            val creature = ElCreature(
                e.entity,
                Stat.MAX_HEALTH to health
            )
        }
    }
}
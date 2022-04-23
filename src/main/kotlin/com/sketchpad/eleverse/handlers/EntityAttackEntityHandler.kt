package com.sketchpad.eleverse.handlers

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.entity.ElCreature
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent

class EntityAttackEntityHandler: Listener {
    @EventHandler
    fun manageEleverseHit(e: EntityDamageByEntityEvent) {
        if (e.entity is LivingEntity && e.damager is LivingEntity && e.entity.type != EntityType.ARMOR_STAND) {
            e.damage = 0.0
            val victim = ElCreature(e.entity as LivingEntity)
            if (e.damager is Player) {
                Eleverse.getEleversePlayer(e.damager as Player).damage(e.entity as LivingEntity)
            } else {
                val damage = ElCreature(e.damager as LivingEntity).calculateDamage()
                val newHealth = victim.getStat(Stat.HEALTH)-damage
                victim.setStat(Stat.HEALTH, newHealth)
                victim.updateDisplayName()
                if (newHealth<0) {
                    victim.kill()
                }
            }
        }
    }
}
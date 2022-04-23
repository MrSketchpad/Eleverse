package com.sketchpad.eleverse.entity

import com.google.gson.Gson
import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.stats.StatHolder
import com.sketchpad.eleverse.utils.green
import com.sketchpad.eleverse.utils.yellow
import org.bukkit.ChatColor
import org.bukkit.NamespacedKey
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Monster
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

class ElCreature(entity: LivingEntity,
                 vararg stats: Pair<Stat, Double>,
                 var level: Int = entity.maxHealth.toInt(),
                 var name: String = entity.name): ElEntity(entity, *stats) {
    init {
        if (!entity.persistentDataContainer.has(NamespacedKey(Eleverse.instance, "info"), PersistentDataType.STRING)) {
            entity.persistentDataContainer.set(NamespacedKey(Eleverse.instance, "info"),
                PersistentDataType.STRING, Gson().toJson(CreatureInfo(
                    StatHolder(*stats), name, level
                )))
            setStat(Stat.HEALTH, getStat(Stat.MAX_HEALTH))
            entity.isCustomNameVisible = true
        } else {
            val info = Gson().fromJson(entity.persistentDataContainer.get(NamespacedKey(Eleverse.instance, "info"), PersistentDataType.STRING), CreatureInfo::class.java)
            level = info.level
            name = info.name
        }
        updateDisplayName()
    }
    fun kill() {
        entity.health = 0.0
    }
    fun kill(killer: Player) {
        entity.killer = killer
        entity.health = 0.0
    }
    fun updateDisplayName() {
        val health = getStat(Stat.HEALTH)
        val maxHealth = getStat(Stat.MAX_HEALTH)
        entity.customName = "${
            if (entity is Monster)
                ChatColor.RED
            else
                ChatColor.GRAY
        }$name ${
            if (maxHealth/2>=health) 
                yellow("(${health.toInt()}/${maxHealth.toInt()})")
            else
                green("(${health.toInt()}/${maxHealth.toInt()})")
        }"
    }
    fun damage(dmg: Double) {
        setStat(Stat.HEALTH, getStat(Stat.HEALTH)-dmg)
        updateDisplayName()
    }
    override fun getStat(stat: Stat): Double {
        val info = Gson().fromJson(entity.persistentDataContainer.get(NamespacedKey(Eleverse.instance, "info"), PersistentDataType.STRING), CreatureInfo::class.java)
        return info.stats.getStat(stat)
    }
    override fun setStat(stat: Stat, value: Double) {
        val info = Gson().fromJson(entity.persistentDataContainer.get(NamespacedKey(Eleverse.instance, "info"), PersistentDataType.STRING), CreatureInfo::class.java)
        info.stats.setStat(stat, value)
        entity.persistentDataContainer.set(NamespacedKey(Eleverse.instance, "info"), PersistentDataType.STRING, Gson().toJson(info))
    }
}
package com.sketchpad.eleverse.entity

import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.items.constants.Items
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.stats.StatHolder
import org.bukkit.entity.LivingEntity

/**
 * Represents an Eleverse entity
 */
open class ElEntity(
    val entity: LivingEntity,
    vararg baseStats: Pair<Stat, Double>): StatHolder()
{
    val baseStats: StatHolder = StatHolder()
    init {
        for (stat in Stat.values()) {
            this.baseStats.statsMap[stat] = 0.0
        }
        for (pair in baseStats) {
            this.baseStats.statsMap[pair.first] = pair.second
        }
    }
    open fun calculateDamage(): Double {
        var totalStats = getHelmet().base.combine(
            getChestplate().base.combine(
                getLeggings().base.combine(
                    getBoots().base.combine(
                        getMainHand().base.combine(
                            getOffHand().base)))))
        totalStats = totalStats.combine(baseStats)
        val damage = totalStats.getStat(Stat.DAMAGE)*(1+(totalStats.getStat(Stat.STRENGTH)/50))
        return damage
    }
    fun getHelmet(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.helmet)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
    fun getChestplate(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.chestplate)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
    fun getLeggings(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.leggings)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
    fun getBoots(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.boots)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
    fun getMainHand(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.itemInMainHand)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
    fun getOffHand(): ElItem {
        if (entity.equipment!=null)
            return ElItem.fromItemStack(entity.equipment!!.itemInOffHand)
        return ElItem(Items.NOTHING_AT_ALL.item)
    }
}
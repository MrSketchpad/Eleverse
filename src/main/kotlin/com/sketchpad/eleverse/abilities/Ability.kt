package com.sketchpad.eleverse.abilities

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.stats.Stat
import io.papermc.paper.event.block.BlockBreakBlockEvent
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerToggleSneakEvent

open class Ability(
    val trigger: AbilityTrigger,
    val isShift: Boolean,
    val name: String,
    val desc: String,
    val manaCost: Int = 0,
    val lifeForceCost: Int = 0
) {
    fun consumeStats(p: ElPlayer) {
        p.addToStat(Stat.STAMINA, -manaCost.toDouble())
        p.addToStat(Stat.LIFE_FORCE, -lifeForceCost.toDouble())
    }
    open fun onRightClick(e: PlayerInteractEvent) {}
    open fun onLeftClick(e: PlayerInteractEvent) {}
    open fun onJump(e: PlayerJumpEvent) {}
    open fun onBreakBlock(e: BlockBreakEvent) {}
    open fun onHit(e: EntityDamageByEntityEvent) {}
    open fun onShift(e: PlayerToggleSneakEvent) {}
}
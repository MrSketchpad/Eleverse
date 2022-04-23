package com.sketchpad.eleverse.handlers

import com.destroystokyo.paper.event.player.PlayerJumpEvent
import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.abilities.AbilityTrigger
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.items.ElItem.Companion.fromItemStack
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerInteractEvent

class AbilityHandler: Listener {
    @EventHandler
    fun click(e: PlayerInteractEvent) {
        for (ab in fromItemStack(e.player.inventory.itemInMainHand).base.abilities) {
            // Checks the type of click made by the player, and whether the current ability is triggered by such a click.
            if (ab.trigger==AbilityTrigger.RIGHT_CLICK && (e.action==Action.RIGHT_CLICK_AIR || e.action==Action.RIGHT_CLICK_BLOCK)) {
                if (ab.isShift) {
                    if (e.player.isSneaking) {
                        ab.onRightClick(e)
                        ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                    }
                } else {
                    ab.onRightClick(e)
                    ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                }
            }
            else if (ab.trigger==AbilityTrigger.LEFT_CLICK && (e.action==Action.LEFT_CLICK_AIR || e.action==Action.LEFT_CLICK_BLOCK)) {
                if (ab.isShift) {
                    if (e.player.isSneaking) {
                        ab.onLeftClick(e)
                        ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                    }
                } else {
                    ab.onLeftClick(e)
                    ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                }
            }
        }
    }

    @EventHandler
    fun breakBock(e: BlockBreakEvent) {
        for (ab in fromItemStack(e.player.inventory.itemInMainHand).base.abilities) {
            if (ab.trigger==AbilityTrigger.BREAK_BLOCK) {
                if (ab.isShift) {
                    if (e.player.isSneaking) {
                        ab.onBreakBlock(e)
                        ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                    }
                } else {
                    ab.onBreakBlock(e)
                    ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                }
            }
        }
    }

    @EventHandler
    fun jump(e: PlayerJumpEvent) {
        for (ab in fromItemStack(e.player.inventory.itemInMainHand).base.abilities) {
            if (ab.trigger==AbilityTrigger.JUMP) {
                if (ab.isShift) {
                    if (e.player.isSneaking) {
                        ab.onJump(e)
                        ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                    }
                } else {
                    ab.onJump(e)
                    ab.consumeStats(Eleverse.getEleversePlayer(e.player))
                }
            }
        }
    }
}
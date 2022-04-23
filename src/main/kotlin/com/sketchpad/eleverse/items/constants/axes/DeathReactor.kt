package com.sketchpad.eleverse.items.constants.axes

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.abilities.Ability
import com.sketchpad.eleverse.abilities.AbilityTrigger
import com.sketchpad.eleverse.abilities.constants.Abilities
import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.items.constants.Items
import com.sketchpad.eleverse.utils.yellow
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.block.Block
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.Player
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.player.PlayerInteractEvent

class DeathReactor: BaseElItem(
    "The Death Reactor",
    Material.NETHERITE_AXE, Potency.DIVINE, 11,
    "Born by the killer of the tree; the flash of its blade strikes fear into the creatures of the woods.",
    listOf(Abilities.DEATH_OF_THE_FOREST, Abilities.IMPENDING_DOOM),
    hashMapOf("blocks" to "0", "maxChain" to "5")) {

    class DeathOfTheForest: Ability(
        AbilityTrigger.BREAK_BLOCK, false,
        "Death of the Forest",
        "Breaking a block of wood will cause a chain reaction that destroys all wood that is " +
                "touching it. The more wood you break, the more blocks will be affected. " +
                "#n #n Blocks broken: ${yellow("[blocks]")} " +
                "#n Maximum chain: ${yellow("[maxChain]")}", 10) {
        override fun onBreakBlock(e: BlockBreakEvent) {
            val type = e.block.type

            if (type== Material.ACACIA_LOG ||
                type== Material.BIRCH_LOG ||
                type== Material.OAK_LOG ||
                type== Material.JUNGLE_LOG ||
                type== Material.SPRUCE_LOG ||
                type== Material.DARK_OAK_LOG ||
                type== Material.CRIMSON_STEM ||
                type== Material.WARPED_STEM)
            {
                evaluateAdjacentBlocks(
                    e.block,
                    type,
                    0,
                    ElItem.fromItemStack(e.player.inventory.itemInMainHand).attributes["maxChain"]!!.toInt() - 2,
                    e.player
                )
            }
        }

        /**
         * Recursive function for death reactor's ability
         */
        private fun evaluateAdjacentBlocks(block: Block, type: Material, timesBroken: Int, maxBreaks: Int, p: Player) {
            for (b in listOf(
                block.getRelative(0, 0, 1),
                block.getRelative(0, 0, -1),
                block.getRelative(0, 1, 0),
                block.getRelative(0, -1, 0),
                block.getRelative(1, 0, 0),
                block.getRelative(-1, 0, 0),
            )) {
                Eleverse.instance.server.scheduler.scheduleSyncDelayedTask(Eleverse.instance, {
                    block.breakNaturally()
                    block.world.playSound(block.location, Sound.BLOCK_WOOD_BREAK, 0.1f, 1f)
                    if (b.type==type && timesBroken<=maxBreaks) {
                        val newItem = ElItem.fromItemStack(p.inventory.itemInMainHand)
                        if (newItem.base== Items.DEATH_REACTOR.item) {
                            newItem.attributes["blocks"] = (newItem.attributes["blocks"]!!.toInt()+1).toString()
                            newItem.attributes["maxChain"] = (((newItem.attributes["blocks"]!!.toInt()+1)/50)+5).toString()
                            p.inventory.setItemInMainHand(newItem.toItemStack())
                        }
                        evaluateAdjacentBlocks(b, type, timesBroken+1, maxBreaks, p)
                    }
                }, 1)
            }
        }
    }

    class ImpendingDoom: Ability(
        AbilityTrigger.RIGHT_CLICK, false,
        "Impending Doom",
        "Throw the axe, activating Death of the Forest on all blocks that it hits.", 25
    ) {
        override fun onRightClick(e: PlayerInteractEvent) {
            val stand: ArmorStand = e.player.world.spawnEntity(e.player.location, EntityType.ARMOR_STAND) as ArmorStand
            stand.isVisible = false
            stand.equipment.setItemInMainHand(e.player.inventory.itemInMainHand)
            val velocity = e.player.location.direction
            Bukkit.getScheduler().scheduleSyncRepeatingTask(Eleverse.instance, {
                stand.velocity = velocity
            }, 0, 1)
        }
    }
}


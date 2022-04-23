package com.sketchpad.eleverse.items.constants.swords

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.abilities.Ability
import com.sketchpad.eleverse.abilities.AbilityTrigger
import com.sketchpad.eleverse.abilities.constants.Abilities
import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.utils.green
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector

class DevelopersFalchion: BaseElItem(
    "The Developer's Falchion", Material.DIAMOND_SWORD, Potency.THREATENING, 10, "",
    listOf(Abilities.IS_THIS_WORKING), hashMapOf("enabled" to "false"), hashMapOf(), Stat.MAX_HEALTH to 100.0
) {
    class IsThisWorking: Ability(
        AbilityTrigger.RIGHT_CLICK,
        false,
        "Is this working??",
        "Does whatever it is I'm testing at the moment! Currently:"+
                "Pushes you down if this ability is enabled! #n Enabled: ${green("[enabled]")}") {
        override fun onRightClick(e: PlayerInteractEvent) {
            val player = Eleverse.getEleversePlayer(e.player)

            val item = player.getMainHand()
            if (item.attributes["enabled"]=="true") {
                item.attributes["enabled"] = "false"
            } else {
                item.attributes["enabled"] = "true"
            }
            e.player.inventory.setItemInMainHand(item.toItemStack())
            val id = Bukkit.getScheduler().scheduleSyncRepeatingTask(Eleverse.instance, {
                if (player.getMainHand().attributes["enabled"]=="true") {
                    e.player.velocity = Vector(0, -1, 0)
                }
            }, 0, 1)
        }
    }
}
package com.sketchpad.eleverse.items.constants.armor.boots

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class BootsOfTheTest: BaseElItem("Boots of the Test",
    Material.DIAMOND_BOOTS, Potency.LETHAL, 10, "", listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 70.0,
    Stat.MAX_STAMINA to 70.0) {
}
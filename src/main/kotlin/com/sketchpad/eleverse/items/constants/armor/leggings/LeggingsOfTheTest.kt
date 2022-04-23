package com.sketchpad.eleverse.items.constants.armor.leggings

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class LeggingsOfTheTest: BaseElItem("Leggings of the Test",
    Material.DIAMOND_LEGGINGS, Potency.LETHAL, 10, "", listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 85.0,
    Stat.MAX_STAMINA to 85.0) {
}
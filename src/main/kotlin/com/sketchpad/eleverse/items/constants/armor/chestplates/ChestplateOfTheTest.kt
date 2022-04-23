package com.sketchpad.eleverse.items.constants.armor.chestplates

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class ChestplateOfTheTest: BaseElItem("Chestplate of the Test",
    Material.DIAMOND_CHESTPLATE, Potency.LETHAL, 10, "", listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 100.0,
    Stat.MAX_STAMINA to 100.0) {
}
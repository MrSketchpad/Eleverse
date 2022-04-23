package com.sketchpad.eleverse.items.constants.armor.helmets

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class HelmetOfTheTest: BaseElItem("Helmet of the Test",
    Material.DIAMOND_HELMET, Potency.LETHAL, 10, "", listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 75.0,
    Stat.MAX_STAMINA to 75.0) {
}
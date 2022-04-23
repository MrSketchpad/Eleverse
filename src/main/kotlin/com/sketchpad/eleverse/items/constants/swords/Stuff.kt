package com.sketchpad.eleverse.items.constants.swords

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class Stuff: BaseElItem(
    "Stuff",
    Material.GOLDEN_SWORD, Potency.OMNIPOTENT, 10, "",
    listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 100.0,
    Stat.MAX_STAMINA to 100.0,
    Stat.MAX_LIFE_FORCE to 100.0,
    Stat.DAMAGE to 25.0,
) {
}
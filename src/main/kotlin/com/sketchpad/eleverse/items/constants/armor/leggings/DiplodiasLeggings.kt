package com.sketchpad.eleverse.items.constants.armor.leggings

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class DiplodiasLeggings: BaseElItem("Diplodia's Leggings",
    Material.LEATHER_LEGGINGS, Potency.LETHAL, 11,
    "That who wears this armor bestows upon themselves the title of the tree eater.",
    listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 110.0,
    Stat.MAX_STAMINA to 50.0,
    Stat.SPEED to 15.0) {
}
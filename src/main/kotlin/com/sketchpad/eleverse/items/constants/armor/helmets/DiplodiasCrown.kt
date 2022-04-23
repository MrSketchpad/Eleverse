package com.sketchpad.eleverse.items.constants.armor.helmets

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import com.sketchpad.eleverse.stats.Stat
import org.bukkit.Material

class DiplodiasCrown: BaseElItem("Diplodia's Crown",
    Material.PLAYER_HEAD, Potency.LETHAL, 11,
    "That who wears this armor bestows upon themselves the title of the tree eater.",
    listOf(), hashMapOf(), hashMapOf(),
    Stat.MAX_HEALTH to 100.0,
    Stat.MAX_STAMINA to 50.0,
    Stat.SPEED to 3.0) {
}
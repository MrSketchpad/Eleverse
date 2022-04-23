package com.sketchpad.eleverse.items

import com.sketchpad.eleverse.abilities.Ability
import com.sketchpad.eleverse.abilities.constants.Abilities
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.stats.StatHolder
import org.bukkit.Material

open class BaseElItem(val name: String,
                      val material: Material,
                      val potency: Potency,
                      val value: Int,
                      val description: String = "",
                      abilities: List<Abilities> = listOf(),
                      val attributes: HashMap<String, String> = hashMapOf(),
                      val boundAttributes: HashMap<Pair<String, Stat>, Int> = hashMapOf(),
                      vararg stats: Pair<Stat, Double>,
): StatHolder(*stats) {
    val abilities = mutableListOf<Ability>()
    val id: String

    init {
        for (ability in abilities) {
            this.abilities.add(ability.ability)
        }
        id = name.uppercase().replace(" ", "_").replace("'", "")
    }
}
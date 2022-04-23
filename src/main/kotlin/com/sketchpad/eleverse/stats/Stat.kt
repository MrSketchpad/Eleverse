package com.sketchpad.eleverse.stats

import org.bukkit.ChatColor
import org.bukkit.scoreboard.Criterias.HEALTH

enum class Stat(val displayName: String, val displayColor: ChatColor) {
    STAMINA("Stamina", ChatColor.AQUA),
    MAX_STAMINA("Stamina", ChatColor.AQUA),

    LIFE_FORCE("Life Force", ChatColor.DARK_PURPLE),
    MAX_LIFE_FORCE("Life Force", ChatColor.DARK_PURPLE),

    HEALTH("Health", ChatColor.RED),
    MAX_HEALTH("Health", ChatColor.RED),

    STRENGTH("Strength", ChatColor.RED),
    DAMAGE("Damage", ChatColor.GREEN),
    SPEED("Speed", ChatColor.WHITE)
    ;
}
package com.sketchpad.eleverse.items

import org.bukkit.ChatColor

enum class Potency(val color: ChatColor) {
    HARMLESS(ChatColor.WHITE),
    WEAK(ChatColor.GREEN),
    THREATENING(ChatColor.BLUE),
    DANGEROUS(ChatColor.RED),
    POWERFUL(ChatColor.AQUA),
    DIVINE(ChatColor.DARK_PURPLE),
    LETHAL(ChatColor.GOLD),
    OMNIPOTENT(ChatColor.LIGHT_PURPLE),
    ;
}
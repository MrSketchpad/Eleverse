package com.sketchpad.eleverse.utils

import com.sketchpad.eleverse.Eleverse
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.TextColor
import org.bukkit.ChatColor

fun log(msg: String) {
    Eleverse.instance.server.consoleSender.sendMessage(msg)
}

fun black(s: String): String {
    val newS = s.replace("§r", "§0")
    return "§0$newS§r"
}

fun darkBlue(s: String): String {
    val newS = s.replace("§r", "§1")
    return "§1$newS§r"
}

fun darkGreen(s: String): String {
    val newS = s.replace("§r", "§2")
    return "§2$newS§r"
}

fun darkAqua(s: String): String {
    val newS = s.replace("§r", "§3")
    return "§3$newS§r"
}

fun darkRed(s: String): String {
    val newS = s.replace("§r", "§4")
    return "§4$newS§r"
}

fun gold(s: String): String {
    val newS = s.replace("§r", "§6")
    return "§6$newS§r"
}

fun darkGray(s: String): String {
    val newS = s.replace("§r", "§8")
    return "§8$newS§r"
}

fun aqua(s: String): String {
    val newS = s.replace("§r", "§b")
    return "§b$newS§r"
}

fun white(s: String): String {
    val newS = s.replace("§r", "§f")
    return "§f$newS§r"
}

fun red(s: String): String {
    val newS = s.replace("§r", "§c")
    return "§c$newS§r"
}

fun green(s: String): String {
    val newS = s.replace("§r", "§a")
    return "§a$newS§r"
}

fun blue(s: String): String {
    val newS = s.replace("§r", "§9")
    return "§9$newS§r"
}

fun yellow(s: String): String {
    val newS = s.replace("§r", "§e")
    return "§e$newS§r"
}

fun purple(s: String): String {
    val newS = s.replace("§r", "§5")
    return "§5$newS§r"
}

fun magenta(s: String): String {
    val newS = s.replace("§r", "§d")
    return "§d$newS§r"
}

fun gray(s: String): String {
    val newS = s.replace("§r", "§7")
    return "§7$newS§r"
}

fun bold(s: String): String {
    val newS = s.replace("§r", "§l")
    return "§l$newS§r"
}

fun italic(s: String): String {
    val newS = s.replace("§r", "§o")
    return "§o$newS§r"
}

fun underline(s: String): String {
    val newS = s.replace("§r", "§n")
    return "§n$newS§r"
}

fun strikethrough(s: String): String {
    val newS = s.replace("§r", "§m")
    return "§m$newS§r"
}

fun magic(s: String): String {
    val newS = s.replace("§r", ChatColor.MAGIC.toString())
    return ChatColor.MAGIC.toString() + newS + "§r"
}

fun custom(red: Int, green: Int, blue: Int, s: String?): String {
    return Component.text(s!!).color(TextColor.color(red, green, blue)).toString()
}
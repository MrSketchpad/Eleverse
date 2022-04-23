package com.sketchpad.eleverse.commands.tabcomplete

import com.sketchpad.eleverse.Eleverse
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Creature
import org.bukkit.entity.Player

class GeneralTabCompleter : TabCompleter {
    override fun onTabComplete(sender: CommandSender, cmd: Command, str: String, args: Array<String>): List<String>? {
        if (cmd.name == "elitem") {
            return Eleverse.itemIDs.keys.toList()
        } else if (cmd.name == "elattribute") {
            if (args.size == 1) {
                return Eleverse.getEleversePlayer(sender as Player).getMainHand().base.attributes.keys.toList()
            }
        }
        return null
    }
}
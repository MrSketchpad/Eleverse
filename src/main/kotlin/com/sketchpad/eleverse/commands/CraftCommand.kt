package com.sketchpad.eleverse.commands

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.menus.constants.Menu
import com.sketchpad.eleverse.utils.red
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CraftCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            Menu.ONE_STAR_CRAFTING.menu.open(Eleverse.getEleversePlayer(sender))
            return true
        } else sender.sendMessage(red("This command can only be used by a player!"))
        return false
    }
}
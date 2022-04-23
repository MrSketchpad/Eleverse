package com.sketchpad.eleverse.commands

import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.utils.red
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ElAttributeCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val item = ElItem.fromItemStack(sender.inventory.itemInMainHand)
            item.attributes[args[0]] = args[1]
            sender.inventory.setItemInMainHand(item.toItemStack())
            return true
        } else sender.sendMessage(red("This command can only executed by a player!"))
        return false
    }
}
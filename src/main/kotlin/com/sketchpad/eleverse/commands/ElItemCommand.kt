package com.sketchpad.eleverse.commands

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.utils.green
import com.sketchpad.eleverse.utils.red
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class ElItemCommand: CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player) {
            val item = ElItem(Eleverse.itemIDs[args[0]]!!)
            sender.inventory.addItem(item.toItemStack())
            sender.sendMessage(green("Gave you ${item.base.name}"))
            return true
        } else sender.sendMessage(red("This command can only executed by a player!"))
        return false
    }
}
package com.sketchpad.eleverse

import com.sketchpad.eleverse.commands.ElAttributeCommand
import com.sketchpad.eleverse.commands.ElItemCommand
import com.sketchpad.eleverse.commands.tabcomplete.GeneralTabCompleter
import com.sketchpad.eleverse.commands.CraftCommand
import com.sketchpad.eleverse.craft.ItemRecipe
import com.sketchpad.eleverse.craft.constants.ElRecipe
import com.sketchpad.eleverse.handlers.*
import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.items.constants.Materials
import com.sketchpad.eleverse.items.constants.Items
import com.sketchpad.eleverse.json.JsonManager
import com.sketchpad.eleverse.json.player.PlayerInfo
import com.sketchpad.eleverse.menus.GUIItem
import com.sketchpad.eleverse.menus.GUIMenu
import com.sketchpad.eleverse.menus.constants.GUIItems
import com.sketchpad.eleverse.menus.constants.Menu
import com.sketchpad.eleverse.menus.constants.crafting.CraftingGuiItems
import com.sketchpad.eleverse.player.ElPlayer
import com.sketchpad.eleverse.utils.green
import com.sketchpad.eleverse.utils.log
import com.sketchpad.eleverse.utils.red
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.ShapedRecipe
import org.bukkit.inventory.ShapelessRecipe
import org.bukkit.plugin.java.JavaPlugin
import java.io.File

class Eleverse: JavaPlugin() {
    companion object {
        lateinit var instance: Eleverse
        val players = mutableListOf<ElPlayer>()
        val guiItems = hashMapOf<String, GUIItem>()
        val items = hashMapOf<String, BaseElItem>()
        val itemIDs = hashMapOf<String, BaseElItem>()
        val recipes = mutableListOf<ItemRecipe>()

        lateinit var INFO_FOLDER: File
        lateinit var PLAYER_INFO_FOLDER: File

        /**
         * @return the Eleverse counterpart of [player] in the [players] hashmap.
         */
        fun getEleversePlayer(player: Player): ElPlayer {
            for (elPlayer in players) {
                if (elPlayer.uuid == player.uniqueId) {
                    return elPlayer
                }
            }
            return ElPlayer(player)
        }
    }

    override fun onEnable() {
        instance = this

        PLAYER_INFO_FOLDER = File(instance.dataFolder.toString() + "\\Players")
        INFO_FOLDER = File(instance.dataFolder.toString())
        INFO_FOLDER.mkdirs()
        PLAYER_INFO_FOLDER.mkdirs()

        server.pluginManager.registerEvents(JoinHandler(), this)
        server.pluginManager.registerEvents(QuitHandler(), this)
        server.pluginManager.registerEvents(EntitySpawnHandler(), this)
        server.pluginManager.registerEvents(EntityAttackEntityHandler(), this)
        server.pluginManager.registerEvents(AbilityHandler(), this)
        server.pluginManager.registerEvents(GuiHandler(), this)
        server.pluginManager.registerEvents(CraftingHandler(), this)
        server.pluginManager.registerEvents(ConvertToElItemHandler(), this)

        getCommand("craft")!!.setExecutor(CraftCommand())
        getCommand("elitem")!!.setExecutor(ElItemCommand())
        getCommand("elitem")!!.tabCompleter = GeneralTabCompleter()
        getCommand("elattribute")!!.setExecutor(ElAttributeCommand())
        getCommand("elattribute")!!.tabCompleter = GeneralTabCompleter()

        val iter = Bukkit.recipeIterator()
        while (iter.hasNext()) {
            val recipe = iter.next()
            if (recipe is ShapedRecipe) {
                val elItems = mutableListOf<Pair<BaseElItem, Int>>()
                for (item in listOf('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i')) {
                    if (recipe.ingredientMap.containsKey(item)) {
                        elItems.add(ElItem.fromItemStack(recipe.ingredientMap[item]).base to 1)
                    } else elItems.add(ElItem.fromItemStack(ItemStack(Material.AIR)).base to 1)
                }
                if (ElItem.fromItemStack(recipe.result).base!=Items.NOTHING_AT_ALL.item)
                    recipes.add(ItemRecipe(ElItem.fromItemStack(recipe.result).base, 1, elItems, recipe.result.amount))
            } else if (recipe is ShapelessRecipe) {
                val elItems = mutableListOf<Pair<BaseElItem, Int>>()
                for (i in 0..8) {
                    if (recipe.choiceList.size>i) {
                        elItems.add(ElItem.fromItemStack(recipe.choiceList[i].itemStack).base to 1)
                    } else elItems.add(ElItem.fromItemStack(ItemStack(Material.AIR)).base to 1)
                }
                if (ElItem.fromItemStack(recipe.result).base!=Items.NOTHING_AT_ALL.item)
                    recipes.add(ItemRecipe(ElItem.fromItemStack(recipe.result).base, 1, elItems, recipe.result.amount, true))
            }
        }
        for (recipe in ElRecipe.values()) {
            recipes.add(recipe.recipe)
        }
        for (recipe in recipes) {
            println(recipe.stringify())
        }

        for (menu in Menu.values()) {
            GUIMenu.menus[menu.menu.name] = menu.menu
        }
        for (item in Materials.values()) {
            items[item.item.name] = item.item
            itemIDs[item.item.id] = item.item
        }
        for (item in Items.values()) {
            items[item.item.name] = item.item
            itemIDs[item.item.id] = item.item
        }

        for (item in GUIItems.values()) {
            guiItems[item.name] = item.item
        }
        for (item in CraftingGuiItems.values()) {
            guiItems[item.name] = item.item
        }

        for (player in Bukkit.getOnlinePlayers()) {
            if (!JsonManager.infoWritten(player)) {
                JsonManager.writePlayerInfo(player, PlayerInfo(hashMapOf()))
            }

            val elPlayer = ElPlayer(player)
            elPlayer.startClock()
            players.add(elPlayer)

            for (item in JsonManager.getPlayerInfo(player).inventory) {
                player.inventory.setItem(item.key, item.value.toItemStack())
            }
        }
        log(green("[Eleverse] Plugin enabled successfully."))
    }
    override fun onDisable() {
        for (player in Bukkit.getOnlinePlayers()) {
            val inventory = hashMapOf<Int, ElItem>()
            for (i in 0..54) {
                val item =  player.inventory.getItem(i)
                if (item!=null) {
                    inventory[i] = ElItem.fromItemStack(item)
                }
            }
            val info = JsonManager.getPlayerInfo(player)
            info.inventory = inventory
            JsonManager.writePlayerInfo(player, info)
        }
        log(red("[Eleverse] Plugin disabled successfully."))
    }
}
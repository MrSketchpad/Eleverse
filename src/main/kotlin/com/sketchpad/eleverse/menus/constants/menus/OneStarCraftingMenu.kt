package com.sketchpad.eleverse.menus.constants.menus

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.craft.ItemRecipe
import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.items.constants.Items
import com.sketchpad.eleverse.menus.GUIMenu
import com.sketchpad.eleverse.menus.constants.GUIItems
import com.sketchpad.eleverse.menus.constants.crafting.CraftingGuiItems
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.ItemStack

class OneStarCraftingMenu: GUIMenu("☆ Crafting",  54,
    2 to CraftingGuiItems.ONE_STAR.item,
    3 to CraftingGuiItems.TWO_STAR.item,
    4 to CraftingGuiItems.THREE_STAR.item,
    5 to CraftingGuiItems.FOUR_STAR.item,
    6 to CraftingGuiItems.FIVE_STAR.item,
    // Crafting grid
    19 to GUIItems.EMPTY.item, 20 to GUIItems.EMPTY.item, 21 to GUIItems.EMPTY.item,
    28 to GUIItems.EMPTY.item, 29 to GUIItems.EMPTY.item, 30 to GUIItems.EMPTY.item, 34 to GUIItems.EMPTY.item,
    37 to GUIItems.EMPTY.item, 38 to GUIItems.EMPTY.item, 39 to GUIItems.EMPTY.item
) {
    override fun onClick(e: InventoryClickEvent) {
        // Crafting checks aren't run if the player didn't click in an inventory
        if (e.clickedInventory==null) return
        // "ghost" inventory is created and updated with the item the player just placed. Turns out the inventory in the event is before the click.
        val inventory = Bukkit.createInventory(null, 54, "☆ Crafting")
        inventory.contents = e.view.topInventory.contents
        if (e.cursor!=null) {
            inventory.setItem(e.slot, e.cursor)
        }
        // All items and their quantities in the crafting grid are saved
        val items = mutableListOf<Pair<BaseElItem, Int>>()
        val itemsMap = hashMapOf<BaseElItem, Int>()
        for (item in listOf(
            inventory.getItem(19), inventory.getItem(20), inventory.getItem(21),
            inventory.getItem(28), inventory.getItem(29), inventory.getItem(30),
            inventory.getItem(37), inventory.getItem(38), inventory.getItem(39))) {
            itemsMap[ElItem.fromItemStack(item).base] = (item?.amount ?: 1)
            items.add(ElItem.fromItemStack(item).base to (item?.amount ?: 1))
        }
        var validRecipe = false
        // Checks this list of items against all recipes until it finds a match.
        for (recipe in Eleverse.recipes) {
            if (recipe.tableTier<=1) {
                val requiredQuantities = mutableListOf<Int>()
                // If the recipe is shapeless, just check if items exist in the correct quantities (ignoring position)
                if (isRecipeValid(recipe, items, requiredQuantities, itemsMap.clone() as HashMap<BaseElItem, Int>)) {
                    val item = ElItem(recipe.output).toItemStack()
                    item.amount = recipe.outputAmount
                    e.view.topInventory.setItem(34, item)
                    validRecipe = true
                    // If the output item is being collected, destroy the items used to craft it.
                    if (e.slot==34 && e.view.topInventory.getItem(34)!=null) {
                        // If the cursor is currently holding an item of the same type as the output, it will add the output to the cursor instead of replacing it
                        e.isCancelled = true
                        if (ElItem.fromItemStack(e.cursor).base==recipe.output) {
                            e.cursor!!.amount += item.amount
                        } else if (e.cursor==null || e.cursor!!.type== Material.AIR) {
                            e.cursor = item
                        }
                        // If the cursor is neither empty nor the same type, nothing happens.
                        else {
                            return
                        }
                        // Destroy items
                        for ((index, i) in listOf(19, 20, 21, 28, 29, 30, 37, 38, 39).withIndex()) {
                            if (recipe.shapeless) {
                                var newItem = inventory.getItem(i)
                                if (newItem==null) newItem = ItemStack(Material.STONE)
                                val elItem = ElItem.fromItemStack(newItem)
                                if (recipe.itemsMap.containsKey(elItem.base))
                                    newItem.amount = newItem.amount- recipe.itemsMap[ElItem.fromItemStack(newItem).base]!!
                                else newItem.amount = 0
                                e.view.topInventory.setItem(i, newItem)
                            } else {
                                var newItem = inventory.getItem(i)
                                if (newItem==null) newItem = ItemStack(Material.STONE)
                                newItem.amount = newItem.amount-requiredQuantities[index]
                                e.view.topInventory.setItem(i, newItem)
                            }
                        }
                        items.clear()
                        requiredQuantities.clear()
                        itemsMap.clear()
                        // Checks if an item can still be crafted with what's left in the crafting grid
                        for (newItem in listOf(
                            e.view.topInventory.getItem(19), e.view.topInventory.getItem(20), e.view.topInventory.getItem(21),
                            e.view.topInventory.getItem(28), e.view.topInventory.getItem(29), e.view.topInventory.getItem(30),
                            e.view.topInventory.getItem(37), e.view.topInventory.getItem(38), e.view.topInventory.getItem(39))) {
                            itemsMap[ElItem.fromItemStack(newItem).base] = (newItem?.amount ?: 1)
                            items.add(ElItem.fromItemStack(newItem).base to (newItem?.amount ?: 1))
                        }
                        if (isRecipeValid(recipe, items, requiredQuantities, itemsMap.clone() as HashMap<BaseElItem, Int>)) {
                            Bukkit.getServer().scheduler.scheduleSyncDelayedTask(Eleverse.instance) {
                                e.view.topInventory.setItem(34, item)
                            }
                        } else {
                            e.view.topInventory.setItem(34, ItemStack(Material.AIR))
                        }
                        return
                    }
                    break
                }
            }
        }
        // If no match is found, the output item is set to air
        if (!validRecipe) {
            e.inventory.setItem(34, ItemStack(Material.AIR))
        }
    }

    fun isRecipeValid(recipe: ItemRecipe, items: List<Pair<BaseElItem, Int>>, requiredQuantities: MutableList<Int>, itemsMap: HashMap<BaseElItem, Int>): Boolean {
        var valid = true
        // If the recipe is shapeless, just check if items exist in the correct quantities (ignoring position)
        if (recipe.shapeless) {
            for (item in recipe.items) {
                if (!((itemsMap.containsKey(item.first) && itemsMap[item.first]!! >= item.second) || item.first== Items.NOTHING_AT_ALL.item)) {
                    valid = false
                    break
                } else itemsMap.remove(item.first)
            }
            // If there are extra items in the grid the recipe is wrong
            val newItemsMap = hashMapOf<BaseElItem, Int>()
            for (item in itemsMap) {
                if (item.key!= Items.NOTHING_AT_ALL.item) {
                    newItemsMap[item.key] = item.value
                }
            }
            if (newItemsMap.size>0) {
                valid = false
            }
        } else {
            for ((i, item) in items.withIndex()) {
                requiredQuantities.add(recipe.items[i].second)
                if (!(item.first==recipe.items[i].first && item.second>=recipe.items[i].second)) {
                    valid = false
                    break
                }
            }
        }
        return valid
    }
}
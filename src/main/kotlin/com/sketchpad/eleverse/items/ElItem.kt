package com.sketchpad.eleverse.items

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.items.constants.Materials
import com.sketchpad.eleverse.items.constants.Items
import com.sketchpad.eleverse.items.nbt.ItemInfo
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.stats.StatHolder
import com.sketchpad.eleverse.utils.*
import de.tr7zw.nbtapi.NBTItem
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import java.lang.IllegalArgumentException

class ElItem(val base: BaseElItem) {
    companion object {
        /**
         * @return an [ElItem] with all the stats of the [stack]
         */
        fun fromItemStack(stack: ItemStack?): ElItem {
            if (stack != null && stack.type != Material.AIR) {
                if (NBTItem(stack).hasKey("info")) {
                    val key = NBTItem(stack).getObject("info", ItemInfo::class.java).name
                    if (stack.hasItemMeta() && Eleverse.items.containsKey(key)) {
                        val baseItem = ElItem(Eleverse.items[key]!!)
                        baseItem.attributes = NBTItem(stack).getObject("info", ItemInfo::class.java).attributes
                        baseItem.amount = stack.amount
                        return baseItem
                    }
                } else {
                    try {
                        val item = ElItem(Materials.valueOf(stack.type.name).item)
                        item.amount = stack.amount
                        return item
                    } catch (e: Exception) {}
                }
            }
            return ElItem(Items.NOTHING_AT_ALL.item)
        }
    }

    var attributes: HashMap<String, String> = HashMap(base.attributes)
    var amount: Int = 1

    fun getStats(): StatHolder {
        val stats = base.combine(StatHolder())
        for (bind in base.boundAttributes) {
            if (attributes.containsKey(bind.key.first)) {
                try {
                    stats.addToStat(bind.key.second, (attributes[bind.key.first]!!.toInt()*bind.value).toDouble())
                } catch (e: Exception) {
                    throw IllegalArgumentException("You cannot bind a stat to a non-number attribute!")
                }
            }
        }
        return stats
    }
    /**
     * @return an [ItemStack] with the lore and NBT of this item.
     */
    fun toItemStack(): ItemStack {
        val base = Eleverse.items[this.base.name]!!
        var stack = ItemStack(base.material)
        val meta = stack.itemMeta
        meta.addItemFlags(
            ItemFlag.HIDE_ATTRIBUTES,
            ItemFlag.HIDE_DESTROYS,
            ItemFlag.HIDE_DYE,
            ItemFlag.HIDE_ENCHANTS,
            ItemFlag.HIDE_PLACED_ON,
            ItemFlag.HIDE_POTION_EFFECTS,
            ItemFlag.HIDE_UNBREAKABLE
        )

        // Stats
        val stats = getStats()

        meta.setDisplayName("${base.potency.color}${base.name} ${if (base.value>10) ChatColor.GOLD else ""}${"☆".repeat(base.value/2)}")
        val lore = mutableListOf<String>()
        // Stats label
        var displayStats = false
        for (stat in Stat.values()) {
            if (stats.getStat(stat)!=0.0) {
                displayStats = true
                lore.add(gray("${stat.displayName}: ${stat.displayColor}${formatWithCommas(stats.getStat(stat).toString(), true)}"))
            }
        }
        if (displayStats) lore.add("")

        // Ability info
        for (ability in base.abilities) {
            lore.add("${gold(ability.name)} ${yellow(bold(ability.trigger.name.replace("_", " ") + if (ability.isShift) "+SHIFT" else ""))}")
            for (line in ability.desc.formatForDesc(attributes)) {
                lore.add(gray(line))
            }
            if (ability.manaCost!=0) {
                lore.add(darkGray("Mana Cost: ${aqua(ability.manaCost.toString())}"))
            }
            if (ability.lifeForceCost!=0) {
                lore.add(darkGray("Life Force Cost: ${purple(ability.manaCost.toString())}"))
            }
            lore.add("")
        }

        // Description
        for (line in base.description.formatForDesc(attributes)) {
            lore.add(gray(italic(line)))
        }
        if (base.description.isNotEmpty()) {
            lore.add("")
        }

        // Potency label
        lore.add("${base.potency.color}"+bold(base.potency.name))

        meta.lore = lore
        stack.itemMeta = meta

        val nbt = NBTItem(stack)
        val info = ItemInfo(base.name, stats.statsMap, attributes)
        nbt.setObject("info", info)
        stack = nbt.item
        stack.amount = amount

        return stack
    }
}
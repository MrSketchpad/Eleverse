package com.sketchpad.eleverse.craft

import com.sketchpad.eleverse.items.BaseElItem
import java.lang.StringBuilder

class ItemRecipe(val output: BaseElItem, val tableTier: Int, val items: List<Pair<BaseElItem, Int>>, val outputAmount: Int = 1, val shapeless: Boolean = false) {
    val itemsMap = hashMapOf<BaseElItem, Int>()
    init {
        for (item in items) {
            itemsMap[item.first] = item.second
        }
    }
    fun stringify(): String {
        val builder = StringBuilder()
        var total = 0
        for (i in 0..2) {
            for (j in 0..2) {
                builder.append(items[total].first.name+", ")
                total++
            }
            builder.append("\n")
        }
        builder.append("Creates: ${output.name}\n")
        return builder.toString()
    }
}
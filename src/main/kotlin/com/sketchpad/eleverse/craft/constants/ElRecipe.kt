package com.sketchpad.eleverse.craft.constants

import com.sketchpad.eleverse.craft.ItemRecipe
import com.sketchpad.eleverse.items.constants.Items

enum class ElRecipe(val recipe: ItemRecipe) {
    STUFF(ItemRecipe(Items.STUFF.item, 1, listOf(
        Items.DEATH_REACTOR.item to 1, Items.DEATH_REACTOR.item to 1, Items.DEATH_REACTOR.item to 1,
        Items.NOTHING_AT_ALL.item to 1, Items.DEATH_REACTOR.item to 1, Items.NOTHING_AT_ALL.item to 1,
        Items.NOTHING_AT_ALL.item to 1, Items.DEATH_REACTOR.item to 1, Items.NOTHING_AT_ALL.item to 1
    ))),
    ;
}
package com.sketchpad.eleverse.menus.constants

import com.sketchpad.eleverse.menus.GUIMenu
import com.sketchpad.eleverse.menus.constants.menus.*

enum class Menu(val menu: GUIMenu) {
    ONE_STAR_CRAFTING(OneStarCraftingMenu()),
    TWO_STAR_CRAFTING(TwoStarCraftingMenu()),
    THREE_STAR_CRAFTING(ThreeStarCraftingMenu()),
    FOUR_STAR_CRAFTING(FourStarCraftingMenu()),
    FIVE_STAR_CRAFTING(FiveStarCraftingMenu()),
}
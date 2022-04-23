package com.sketchpad.eleverse.items.constants

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.constants.armor.boots.BootsOfTheTest
import com.sketchpad.eleverse.items.constants.armor.boots.DiplodiasBoots
import com.sketchpad.eleverse.items.constants.armor.chestplates.ChestplateOfTheTest
import com.sketchpad.eleverse.items.constants.armor.chestplates.DiplodiasArmor
import com.sketchpad.eleverse.items.constants.armor.helmets.DiplodiasCrown
import com.sketchpad.eleverse.items.constants.armor.helmets.HelmetOfTheTest
import com.sketchpad.eleverse.items.constants.armor.leggings.DiplodiasLeggings
import com.sketchpad.eleverse.items.constants.armor.leggings.LeggingsOfTheTest
import com.sketchpad.eleverse.items.constants.axes.DeathReactor
import com.sketchpad.eleverse.items.constants.swords.DevelopersFalchion
import com.sketchpad.eleverse.items.constants.swords.NothingAtAll
import com.sketchpad.eleverse.items.constants.swords.Stuff

enum class Items(val item: BaseElItem) {
    NOTHING_AT_ALL(NothingAtAll()),
    DEVELOPERS_FALCHION(DevelopersFalchion()),
    STUFF(Stuff()),
    DEATH_REACTOR(DeathReactor()),
    DIPLODIAS_CROWN(DiplodiasCrown()),
    DIPLODIAS_ARMOR(DiplodiasArmor()),
    DIPLODIAS_LEGGINGS(DiplodiasLeggings()),
    DIPLODIAS_BOOTS(DiplodiasBoots()),
    HELMET_OF_THE_TEST(HelmetOfTheTest()),
    CHESTPLATE_OF_THE_TEST(ChestplateOfTheTest()),
    LEGGINGS_OF_THE_TEST(LeggingsOfTheTest()),
    BOOTS_OF_THE_TEST(BootsOfTheTest()),
    ;
}
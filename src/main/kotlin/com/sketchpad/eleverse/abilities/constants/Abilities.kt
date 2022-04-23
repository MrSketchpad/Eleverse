package com.sketchpad.eleverse.abilities.constants

import com.sketchpad.eleverse.abilities.Ability
import com.sketchpad.eleverse.items.constants.axes.DeathReactor
import com.sketchpad.eleverse.items.constants.swords.DevelopersFalchion

enum class Abilities(val ability: Ability) {
    IMPENDING_DOOM(DeathReactor.ImpendingDoom()),
    DEATH_OF_THE_FOREST(DeathReactor.DeathOfTheForest()),
    IS_THIS_WORKING(DevelopersFalchion.IsThisWorking()),
    ;
}
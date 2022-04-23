package com.sketchpad.eleverse.items.nbt

import com.sketchpad.eleverse.stats.Stat

class ItemInfo(
    val name: String,
    val stats: HashMap<Stat, Double>,
    val attributes: HashMap<String, String>
) {
}
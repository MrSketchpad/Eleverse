package com.sketchpad.eleverse.items.constants

import com.sketchpad.eleverse.items.BaseElItem
import com.sketchpad.eleverse.items.Potency
import org.bukkit.Material

enum class Materials(val item: BaseElItem) {
    // Harmless
    STONE(BaseElItem("Stone", Material.STONE, Potency.HARMLESS, 0,
        "A common rock found all across the Eleverse.")),

    COBBLESTONE(BaseElItem("Cobblestone", Material.COBBLESTONE, Potency.HARMLESS, 0,
        "This rock is found when stone is destroyed without special tools.")),

    GRASS_BLOCK(BaseElItem("Grass Block", Material.GRASS_BLOCK, Potency.HARMLESS, 0,
        "This simple grass is found all across the Eleverse.")),

    DIRT(BaseElItem("Dirt", Material.DIRT, Potency.HARMLESS, 0,
        "The most common form of dirt in the Eleverse. It's decent for growing basic crops.")),

    SAND(BaseElItem("Sand", Material.SAND, Potency.HARMLESS, 0,
        "This sand is often found in beaches all across the Eleverse.")),

    // Weak
    GRANITE(BaseElItem("Granite", Material.GRANITE, Potency.WEAK, 0,
        "Found underground almost everywhere. It carries small magic capabilities.")),

    ANDESITE(BaseElItem("Andesite", Material.ANDESITE, Potency.WEAK, 0,
        "Found underground almost everywhere. It carries small magic capabilities.")),

    DIORITE(BaseElItem("Diorite", Material.DIORITE, Potency.WEAK, 0,
        "Found underground almost everywhere. It carries small magic capabilities.")),

    DEEPSLATE(BaseElItem("Deepslate", Material.DEEPSLATE, Potency.WEAK, 0,
        "This dense rock is found deep underground, where pressure has caused it to compress.")),

    OAK_LOG(BaseElItem("Oak Log", Material.OAK_LOG, Potency.WEAK, 0,
    "This log was cut from one of the most common trees in the Eleverse, the oak tree. It is quite weak.")),

    SPRUCE_LOG(BaseElItem("Spruce Log", Material.SPRUCE_LOG, Potency.WEAK, 0,
        "This log was cut from a spruce tree. It's not very dense.")),

    BIRCH_LOG(BaseElItem("Birch Log", Material.BIRCH_LOG, Potency.WEAK, 0,
        "One of the weakest woods in the Eleverse, this log is commonly found in forests.")),

    JUNGLE_LOG(BaseElItem("Jungle Log", Material.JUNGLE_LOG, Potency.WEAK, 0,
        "This log is slightly denser than other common logs. It usually belongs to extremely large trees.")),

    ACACIA_LOG(BaseElItem("Acacia Log", Material.ACACIA_LOG, Potency.WEAK, 0,
        "A brittle log, commonly found in deserts.")),

    DARK_OAK_LOG(BaseElItem("Dark Oak Log", Material.DARK_OAK_LOG, Potency.WEAK, 0,
        "The toughest common log. It belongs to thick, short trees and is often found near mushrooms.")),

    OAK_PLANKS(BaseElItem("Oak Planks", Material.OAK_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from an oak log.")),

    SPRUCE_PLANKS(BaseElItem("Spruce Planks", Material.SPRUCE_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from a spruce log.")),

    BIRCH_PLANKS(BaseElItem("Birch Planks", Material.BIRCH_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from a birch log.")),

    JUNGLE_PLANKS(BaseElItem("Jungle Planks", Material.JUNGLE_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from a jungle log.")),

    ACACIA_PLANKS(BaseElItem("Acacia Planks", Material.ACACIA_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from an acacia log.")),

    DARK_OAK_PLANKS(BaseElItem("Dark Oak Planks", Material.DARK_OAK_PLANKS, Potency.WEAK, 0,
        "A very common material for building. These planks were made from a dark oak log.")),

    ;
}
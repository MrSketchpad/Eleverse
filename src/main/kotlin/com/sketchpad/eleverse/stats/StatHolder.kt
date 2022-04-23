package com.sketchpad.eleverse.stats

/**
 * Represents anything that can hold stats
 */
open class StatHolder(vararg tempStats: Pair<Stat, Double>) {
    var statsMap = hashMapOf<Stat, Double>()
    var multiplier = 1.0
    init {
        for (stat in Stat.values()) {
            this.statsMap[stat] = 0.0
        }
        for (pair in tempStats) {
            this.statsMap[pair.first] = pair.second
        }
    }

    open fun addToStat(stat: Stat, value: Double) {
        setStat(stat, getStat(stat)+value)
    }
    /**
     * Replaces all the stats of this object with [other]'s stats
     */
    fun replaceWith(other: StatHolder) {
        this.statsMap = other.statsMap
    }
    /**
     * Returns a new [StatHolder] with the combined stats of this and [other].
     */
    fun combine(other: StatHolder): StatHolder {
        val newStat = StatHolder()
        for (stat in statsMap) {
            newStat.setStat(stat.key, stat.value+other.getStat(stat.key))
        }
        return newStat
    }

    /**
     * @return the current value of [stat]
     */
    open fun getStat(stat: Stat): Double {
        return statsMap[stat]!!*multiplier
    }

    /**
     * Sets the value of [stat] to [value]
     */
    open fun setStat(stat: Stat, value: Double) {
        statsMap[stat] = value
    }

    /**
     * Sets the entire hashmap of stats and doubles to [stats]
     */
    fun setStats(stats: HashMap<Stat, Double>) {
        this.statsMap = stats
    }
}
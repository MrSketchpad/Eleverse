package com.sketchpad.eleverse.player

import com.sketchpad.eleverse.Eleverse
import com.sketchpad.eleverse.entity.ElCreature
import com.sketchpad.eleverse.entity.ElEntity
import com.sketchpad.eleverse.items.ElItem
import com.sketchpad.eleverse.stats.Stat
import com.sketchpad.eleverse.stats.StatHolder
import com.sketchpad.eleverse.utils.*
import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.attribute.Attribute
import org.bukkit.entity.ArmorStand
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import java.util.*

/**
 * Represents an Eleverse player with all the information it holds.
 */
class ElPlayer(val player: Player): ElEntity(player) {
    private val regenerativeStats = StatHolder()
    private var stats = StatHolder()
    val uuid = player.uniqueId

    companion object {
        // Base Stats
        const val BASE_STAMINA = 100.0
        const val BASE_LIFE_FORCE = 100.0
        const val BASE_HEALTH = 100.0
        const val BASE_STRENGTH = 10.0
        const val BASE_DAMAGE = 10.0
        const val BASE_SPEED = 10.0
    }

    init {
        regenerativeStats.setStat(Stat.LIFE_FORCE, 100.0)
        regenerativeStats.setStat(Stat.HEALTH, 100.0)
    }

    override fun setStat(stat: Stat, value: Double) {
        if (stat==Stat.STAMINA || stat==Stat.HEALTH || stat == Stat.LIFE_FORCE) {
            regenerativeStats.setStat(stat, value)
        } else {
            stats.setStat(stat, value)
        }
    }

    override fun addToStat(stat: Stat, value: Double) {
        setStat(stat, getStat(stat)+value)
    }

    /**
     * Damages [en] with this player's calculated damage and displays the damage with an armorstand.
     */
    fun damage(en: LivingEntity) {
        val victim = ElCreature(en)
        val damage = calculateDamage()
        val newHealth = victim.getStat(Stat.HEALTH)-damage
        victim.setStat(Stat.HEALTH, newHealth)
        victim.updateDisplayName()
        if (newHealth<0) {
            victim.kill(player)
        }

        val rand = Random()
        val loc = en.location
        loc.x = loc.x + rand.nextInt(13) / 10f
        loc.y = loc.y + rand.nextInt(13) / 10f
        loc.z = loc.z + rand.nextInt(13) / 10f
        val damageDisplay = en.world.spawnEntity(loc, EntityType.ARMOR_STAND) as ArmorStand
        damageDisplay.isVisible = false
        damageDisplay.customName = red("${damage.toInt()}")

        damageDisplay.isCustomNameVisible = true
        damageDisplay.setGravity(false)
        damageDisplay.isSmall = true
        Bukkit.getScheduler().runTaskLater(Eleverse.instance, Runnable { damageDisplay.remove() }, 20)
    }
    override fun calculateDamage(): Double {
        val damage = getStat(Stat.DAMAGE)*(1+(getStat(Stat.STRENGTH)/50))
        return damage
    }
    /**
     * Updates the player's stats so that they're up-to-date with whatever new gear or upgrades they're using.
     */
    fun getStats() {
        var newStats = StatHolder()
        newStats = newStats
            .combine(getHelmet().getStats())
            .combine(getChestplate().getStats())
            .combine(getLeggings().getStats())
            .combine(getBoots().getStats())
            .combine(getMainHand().getStats())
            .combine(getOffHand().getStats())

        // Setting Base Stats
        newStats.addToStat(Stat.MAX_HEALTH, BASE_HEALTH)
        newStats.addToStat(Stat.MAX_STAMINA, BASE_STAMINA)
        newStats.addToStat(Stat.MAX_LIFE_FORCE, BASE_LIFE_FORCE)
        newStats.addToStat(Stat.STRENGTH, BASE_STRENGTH)
        newStats.addToStat(Stat.DAMAGE, BASE_DAMAGE)
        newStats.addToStat(Stat.SPEED, BASE_SPEED)

        stats = newStats
        replaceWith(stats.combine(regenerativeStats))
    }

    /**
     * Begins repeating certain tasks every 10 ticks (0.5 seconds).
     */
    fun startClock() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(Eleverse.instance, {
            //updateInventory()
            getStats()
            updateStats()
            displayStats()
        }, 0, 10)
    }

    /**
     * Displays the stamina and life force stats to the player this object represents.
     */
    fun displayStats() {
        val barCount = 16
        val barSymbol = "■"

        // Creates bar representing the player's Stamina stat
        val staminaPercentage = barCount*(getStat(Stat.STAMINA)/getStat(Stat.MAX_STAMINA))
        var staminaText = barSymbol.repeat(barCount)
        val staminaLength = getStat(Stat.STAMINA).toInt().toString().length
        staminaText = staminaText.substring(0, (barCount/2)-((staminaLength+1)/2))+
                " "+getStat(Stat.STAMINA).toInt()+
                " "+staminaText.substring((barCount/2)+((staminaLength+1)/2), barCount)
        val coloredStaminaText = StringBuilder()

        for ((index, char) in staminaText.toCharArray().withIndex()) {
            coloredStaminaText.append(
                // Ensures bar symbol is only colored if the player's stamina is above a certain percentage of their max stamina
                if (index<=(staminaPercentage)) aqua(char.toString()) else gray(char.toString())
            )
        }

        // Creates bar representing the player's Life Force stat
        // Yes I know using a very similar code block twice is a bad practice
        val lifeForcePercentage = barCount*(getStat(Stat.LIFE_FORCE)/getStat(Stat.MAX_LIFE_FORCE))
        var lifeForceText = barSymbol.repeat(barCount)
        val lifeForceLength = getStat(Stat.LIFE_FORCE).toInt().toString().length
        lifeForceText = lifeForceText.substring(0, (barCount/2)-((lifeForceLength+1)/2))+
                " "+getStat(Stat.LIFE_FORCE).toInt()+
                " "+lifeForceText.substring((barCount/2)+((lifeForceLength+1)/2), barCount)
        val coloredLifeForceText = StringBuilder()

        for ((index, char) in lifeForceText.toCharArray().withIndex()) {
            coloredLifeForceText.append(
                // Ensures bar symbol is only colored if the player's life force is above a certain percentage of their max life force
                if (index<(lifeForcePercentage) || (index==lifeForceText.length-1 && getStat(Stat.LIFE_FORCE)>=getStat(Stat.MAX_LIFE_FORCE))) purple(char.toString()) else gray(char.toString())
            )
        }

        sendActionBar(
            coloredLifeForceText.toString()
                +"   "
                +red("❤ ${getStat(Stat.HEALTH).toInt()}")
                +"   "
                +coloredStaminaText
        )
    }

    /**
     * Updates the player's stats, including their passive regeneration
     */
    fun updateStats() {
        // Increases stamina by 2% of max stamina
        var newStamina = regenerativeStats.getStat(Stat.STAMINA)+(stats.getStat(Stat.MAX_STAMINA)/50)
        newStamina = if (stats.getStat(Stat.MAX_STAMINA)<newStamina) stats.getStat(Stat.MAX_STAMINA) else newStamina
        regenerativeStats.setStat(Stat.STAMINA, newStamina)

        // Increases health by 2% of max health
        var newHealth = regenerativeStats.getStat(Stat.HEALTH)+(stats.getStat(Stat.MAX_HEALTH)/50)
        newHealth = if (stats.getStat(Stat.MAX_HEALTH)<newHealth) stats.getStat(Stat.MAX_HEALTH) else newHealth
        regenerativeStats.setStat(Stat.HEALTH, newHealth)
        // Sets the player's vanilla health proportional to their Eleverse health
        val roundedHealth = (getStat(Stat.MAX_HEALTH)/16.6).toInt().toDouble()
        player.maxHealth = roundedHealth + (roundedHealth % 2)
        if (player.maxHealth>40) player.maxHealth = 40.0
        player.health = player.maxHealth*(regenerativeStats.getStat(Stat.HEALTH)/(stats.getStat(Stat.MAX_HEALTH)))

        // Sets player speed
        player.walkSpeed = (stats.getStat(Stat.SPEED)/50).toFloat()
    }

    /**
     * Updates the player's inventory as needed every 10 ticks
     */
    fun updateInventory() {
        player.inventory.setItemInMainHand(ElItem.fromItemStack(player.inventory.itemInMainHand).toItemStack())
    }

    /**
     * Sends [text] to the player's action bar.
     */
    fun sendActionBar(text: String) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent(text))
    }
}
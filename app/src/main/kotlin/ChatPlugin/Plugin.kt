package ovh.woomy.chatplugin

import io.papermc.paper.event.player.AsyncChatEvent
import java.util.Calendar
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin

/*
 * Simple chat plugin that adds date
 *
 * @author Woomymy
 */
public class Plugin : JavaPlugin(), Listener {
    companion object {
        public val logger = Bukkit.getLogger()
    }

    override fun onEnable() {
        logger.info { "Enabling ${this}" }
        Bukkit.getPluginManager().registerEvents(this, this)
    }

    override fun onDisable() {
        logger.info { "Disabling ${this}" }
    }

    /** Format date to [hh:mm:ss] */
    fun formatDate(): String {
        var cal = Calendar.getInstance()
        return "${cal.get(Calendar.HOUR_OF_DAY)}:${cal.get(Calendar.MINUTE)}:${cal.get(Calendar.SECOND)}"
    }

    /** Add dates in chat messages */
    @EventHandler
    public fun onPlayerChat(chatEvent: AsyncChatEvent) {
        if (chatEvent.isCancelled()) return
        val message = chatEvent.message()
        chatEvent.message(
                Component.text("<${formatDate()}> ", NamedTextColor.GOLD)
                        .append(message.color(NamedTextColor.WHITE))
        )
    }

    /** Add date to join messages */
    @EventHandler
    public fun onPlayerJoin(joinEvent: PlayerJoinEvent) {
        val joinMessage = joinEvent.joinMessage()
        if (joinMessage !== null) {
            joinEvent.joinMessage(
                    Component.text("[${formatDate()}] ", NamedTextColor.YELLOW).append(joinMessage)
            )
        }
    }

    /** Add date to leave message */
    @EventHandler
    public fun onPlayerQuit(quitEvent: PlayerQuitEvent) {
        val quitMessage = quitEvent.quitMessage()
        if (quitMessage !== null) {
            quitEvent.quitMessage(
                    Component.text("[${formatDate()}] ", NamedTextColor.GOLD).append(quitMessage)
            )
        }
    }

    /** Add date to death message */
    @EventHandler
    public fun onPlayerDeath(deathEvent: PlayerDeathEvent) {
        val deathMessage = deathEvent.deathMessage()
        if (deathMessage !== null) {
            deathEvent.deathMessage(
                    Component.text("[${formatDate()}] ", NamedTextColor.GOLD).append(deathMessage)
            )
        }
    }
}

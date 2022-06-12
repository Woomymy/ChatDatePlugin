package ovh.woomy.chatplugin

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import java.util.Calendar
import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.event.entity.PlayerDeathEvent

/*
 * Simple chat plugin that adds date
 * 
 * @author Woomymy
 */
public class Plugin: JavaPlugin(), Listener {
    companion object {
        public val logger = Bukkit.getLogger() 
    }

    override fun onEnable() {
        logger.info { "Enabling ${this}" }
        Bukkit.getPluginManager().registerEvents(this,this);
    }

    override fun onDisable() {
        logger.info {
            "Disabling ${this}"
        }
    }

    /**
     * Format date to [hh:mm:ss]
     */
    fun formatDate(): String {
        var cal = Calendar.getInstance();
        return "[${cal.get(Calendar.HOUR_OF_DAY)}:${cal.get(Calendar.MINUTE)}:${cal.get(Calendar.SECOND)}]"
    }

    /**
     * Add dates in chat messages
     */
    @EventHandler
    public fun onPlayerChat(chatEvent: AsyncPlayerChatEvent) {
        if (chatEvent.isCancelled()) return;
        chatEvent.setFormat("§6${formatDate()}§r <§3%1\$s§f>: %2\$s")
    }

    /**
     * Add date to join messages
     */
    @EventHandler
    public fun onPlayerJoin(joinEvent: PlayerJoinEvent) {
        joinEvent.setJoinMessage("§6${formatDate()}§r ${joinEvent.getJoinMessage()}");
    }

    /**
     * Add date to leave message
     */
    @EventHandler
    public fun onPlayerQuit(quitEvent: PlayerQuitEvent) {
        quitEvent.setQuitMessage("§6${formatDate()}§r ${quitEvent.getQuitMessage()}");
    }

    /**
     * Add date to death message
     */
    @EventHandler
    public fun onPlayerDeath(deathEvent: PlayerDeathEvent) {
        deathEvent.setDeathMessage("§6${formatDate()}§r ${deathEvent.getDeathMessage()}");
    }
}

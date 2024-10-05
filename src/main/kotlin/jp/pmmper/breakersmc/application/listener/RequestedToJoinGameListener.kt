package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.player.event.PlayerRequestedToJoinGame
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class RequestedToJoinGameListener : Listener {
    @EventHandler
    fun execute(event: PlayerRequestedToJoinGame) {
        val result = event.game.respondJoinRequest(event) ?: return
        Bukkit.getPluginManager().callEvent(result.first)
        Bukkit.getPluginManager().callEvent(result.second)
    }
}

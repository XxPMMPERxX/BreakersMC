package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.player.event.PlayerRequestedToJoinTeam
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class RequestedToJoinTeamListener : Listener {
    @EventHandler
    fun listen(event: PlayerRequestedToJoinTeam) {
        val result = event.team.respondJoinRequest(event) ?: return
        Bukkit.getPluginManager().callEvent(result)
    }
}

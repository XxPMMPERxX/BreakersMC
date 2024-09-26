package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.PlayerRequestedToJoinTeam
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class RequestedToJoinTeamListener : Listener {
    @EventHandler
    fun listen(event: PlayerRequestedToJoinTeam) {
        val event = event.team.respondJoinRequest(event.player) ?: return
        Bukkit.getPluginManager().callEvent(event)
    }
}

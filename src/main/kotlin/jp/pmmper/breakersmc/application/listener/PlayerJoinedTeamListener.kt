package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.team.event.PlayerJoinedTeam
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerJoinedTeamListener : Listener {
    @EventHandler
    fun listen(event: PlayerJoinedTeam) {
        TODO("ログの保存")
    }
}

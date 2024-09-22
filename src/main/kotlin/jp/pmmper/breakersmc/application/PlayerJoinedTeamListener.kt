package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.team.event.PlayerJoined
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * チーム参加リスナー
 */
class PlayerJoinedTeamListener : Listener {
    @EventHandler
    fun listen(event: PlayerJoined) {
        TODO("ログの保存")
    }
}

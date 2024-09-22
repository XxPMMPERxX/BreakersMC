package jp.pmmper.breakersmc.model.team.event

import jp.pmmper.breakersmc.model.player.Player
import jp.pmmper.breakersmc.model.team.Team
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerJoined(team: Team, player: Player) : Event() {
    companion object {
        private val handlers = HandlerList()

        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    override fun getHandlers(): HandlerList {
        return handlers
    }
}

package jp.pmmper.breakersmc.model.player.event

import jp.pmmper.breakersmc.model.game.Game
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerDied(val player: Player, val game: Game, val message: String, val killer: Player?) : Event() {
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

package jp.pmmper.breakersmc.model.event

import jp.pmmper.breakersmc.model.game.Game
import jp.pmmper.breakersmc.model.game.Phase
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PhaseChanged(val game: Game, val nextPhase: Phase) : Event() {
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

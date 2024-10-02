package jp.pmmper.breakersmc.model.player.event

import jp.pmmper.breakersmc.model.player.Experience
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class PlayerGainedExperience(val player: Player, val amount: Experience) : Event() {
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

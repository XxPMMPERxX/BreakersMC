package jp.pmmper.breakersmc.model.player.event

import jp.pmmper.breakersmc.model.player.Money
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class Credited(val player: Player, val amount: Money) : Event() {
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

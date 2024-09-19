package jp.pmmper.breakersmc.event

import jp.pmmper.breakersmc.game.Phase
import org.bukkit.event.Cancellable
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

/**
 * フェーズ切替イベント
 *
 * @property previousPhase
 * @property nextPhase
 */
class PhaseChangingEvent(val previousPhase: Phase, val nextPhase: Phase) : Event(), Cancellable {
    companion object {
        private val handlers = HandlerList()

        fun getHandlerList(): HandlerList {
            return handlers
        }
    }

    private var isCancelled = false

    override fun getHandlers(): HandlerList {
        return handlers
    }

    override fun isCancelled(): Boolean {
        return isCancelled
    }

    override fun setCancelled(cancel: Boolean) {
        isCancelled = cancel
    }
}

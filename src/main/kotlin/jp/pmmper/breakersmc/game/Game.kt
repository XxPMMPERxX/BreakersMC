package jp.pmmper.breakersmc.game

import jp.pmmper.breakersmc.event.PhaseChangingEvent
import org.bukkit.Bukkit
import java.time.LocalDateTime

abstract class Game(id: GameID) {
    val id: GameID = id
    var beginAt: LocalDateTime? = null
        protected set
    var endedAt: LocalDateTime? = null
        protected set
    var phase = Phase.CLOSE
        private set

    /**
     * 次フェーズに移行
     *
     * @return 移行できたか
     */
    fun next(): Boolean {
        val nextPhase = when (phase) {
            Phase.CLOSE -> Phase.RECRUITING
            Phase.RECRUITING -> Phase.PREPARE
            Phase.PREPARE -> Phase.IN_GAME
            Phase.IN_GAME -> Phase.FINISHED
            Phase.FINISHED -> Phase.CLOSE
        }

        // フェーズ切替イベント呼び出し
        val event = PhaseChangingEvent(phase, nextPhase)
        Bukkit.getPluginManager().callEvent(event)
        val isCancelled = event.isCancelled

        // イベントがキャンセルされなかった場合、次フェーズに移行
        if (!isCancelled) {
            phase = nextPhase
        }

        return isCancelled
    }
}

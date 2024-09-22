package jp.pmmper.breakersmc.model.game

import jp.pmmper.breakersmc.model.game.event.GamePhaseChanged
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
     * @return
     */
    fun nextPhase(): GamePhaseChanged {
        val nextPhase = when (phase) {
            Phase.CLOSE -> Phase.RECRUITING
            Phase.RECRUITING -> Phase.PREPARE
            Phase.PREPARE -> Phase.IN_GAME
            Phase.IN_GAME -> Phase.FINISHED
            Phase.FINISHED -> Phase.CLOSE
        }

        phase = nextPhase
        return GamePhaseChanged(this, nextPhase)
    }
}

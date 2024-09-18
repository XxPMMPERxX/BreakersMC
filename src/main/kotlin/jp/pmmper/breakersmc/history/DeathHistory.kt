package jp.pmmper.breakersmc.history

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.game.GameID
import java.time.LocalDateTime

data class DeathHistory(
    val dead: AccountID,
    val game: GameID,
    val message: Reason?,
    val killer: AccountID?,
    val datetime: LocalDateTime
)

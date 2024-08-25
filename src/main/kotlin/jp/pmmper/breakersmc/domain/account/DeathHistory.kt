package jp.pmmper.breakersmc.domain.account

import jp.pmmper.breakersmc.domain.Reason
import jp.pmmper.breakersmc.domain.game.GameID
import java.time.ZonedDateTime

class DeathHistory(
    val account: AccountID,
    val game: GameID,
    val message: Reason,
    val killer: AccountID?,
    val dateTime: ZonedDateTime
)

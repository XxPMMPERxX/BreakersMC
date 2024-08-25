package jp.pmmper.breakersmc.domain.account

import jp.pmmper.breakersmc.domain.Reason
import jp.pmmper.breakersmc.domain.game.GameID
import java.time.ZonedDateTime

class DeathHistory(
    val dead: AccountID,
    val game: GameID,
    val message: Reason,
    val killer: AccountID?,
    val datetime: ZonedDateTime
)

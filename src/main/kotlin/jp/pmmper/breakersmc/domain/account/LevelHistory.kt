package jp.pmmper.breakersmc.domain.account

import jp.pmmper.breakersmc.domain.Reason
import java.time.LocalDateTime

data class LevelHistory(
    val account: AccountID,
    val oldLevel: Level,
    val newLevel: Level,
    val reason: Reason,
    val datetime: LocalDateTime
)

package jp.pmmper.breakersmc.history

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Level
import java.time.LocalDateTime

data class LevelHistory(
    val account: AccountID,
    val oldLevel: Level,
    val newLevel: Level,
    val reason: Reason?,
    val datetime: LocalDateTime
)

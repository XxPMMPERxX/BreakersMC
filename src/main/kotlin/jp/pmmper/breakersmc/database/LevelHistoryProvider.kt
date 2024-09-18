package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Level
import jp.pmmper.breakersmc.history.LevelHistory

interface LevelHistoryProvider {
    fun find(account: AccountID): List<LevelHistory>

    fun findLatestLevel(account: AccountID): Level

    fun store(history: LevelHistory)
}

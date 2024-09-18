package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.history.DeathHistory

interface DeathHistoryProvider {
    fun findDeathHistory(account: AccountID): List<DeathHistory>

    fun findKillHistory(account: AccountID): List<DeathHistory>

    fun store(history: DeathHistory)
}

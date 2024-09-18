package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Name
import jp.pmmper.breakersmc.history.NameHistory

interface NameHistoryProvider {
    fun find(account: AccountID): List<NameHistory>

    fun findLatestName(account: AccountID): Name

    fun store(history: NameHistory)
}

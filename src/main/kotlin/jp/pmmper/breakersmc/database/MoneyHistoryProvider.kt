package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Money
import jp.pmmper.breakersmc.history.MoneyHistory

interface MoneyHistoryProvider {
    fun find(account: AccountID): List<MoneyHistory>

    fun findLatestMoney(account: AccountID): Money

    fun store(history: MoneyHistory)
}

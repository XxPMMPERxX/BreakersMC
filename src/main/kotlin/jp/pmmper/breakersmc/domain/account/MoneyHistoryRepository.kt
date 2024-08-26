package jp.pmmper.breakersmc.domain.account

interface MoneyHistoryRepository {
    fun find(account: AccountID): List<MoneyHistory>

    fun findLatestMoney(account: AccountID): Money

    fun store(history: MoneyHistory)
}

package jp.pmmper.breakersmc.domain.account

interface MoneyHistoryRepository {
    fun find(account: AccountID): List<MoneyHistory>

    fun store(history: MoneyHistory)
}

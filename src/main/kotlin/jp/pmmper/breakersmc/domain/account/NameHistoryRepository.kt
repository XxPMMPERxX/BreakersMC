package jp.pmmper.breakersmc.domain.account

interface NameHistoryRepository {
    fun find(account: AccountID): List<NameHistory>

    fun store(history: NameHistory)
}

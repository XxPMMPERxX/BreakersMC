package jp.pmmper.breakersmc.domain.account

interface NameHistoryRepository {
    fun find(account: AccountID): List<NameHistory>

    fun findLatestName(account: AccountID): Name

    fun store(history: NameHistory)
}

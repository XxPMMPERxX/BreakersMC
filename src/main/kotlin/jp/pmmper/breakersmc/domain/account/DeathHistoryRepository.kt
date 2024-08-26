package jp.pmmper.breakersmc.domain.account

interface DeathHistoryRepository {
    fun findDeathHistory(account: AccountID): List<DeathHistory>

    fun findKillHistory(account: AccountID): List<DeathHistory>

    fun store(history: DeathHistory)
}

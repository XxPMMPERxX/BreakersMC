package jp.pmmper.breakersmc.domain.account

interface DeathHistoryRepository {
    fun findDeathHistory(dead: AccountID): List<DeathHistory>

    fun findKillHistory(killer: AccountID): List<DeathHistory>

    fun store(history: DeathHistory)
}

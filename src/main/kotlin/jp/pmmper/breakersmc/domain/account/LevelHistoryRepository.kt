package jp.pmmper.breakersmc.domain.account

interface LevelHistoryRepository {
    fun find(account: AccountID): List<LevelHistory>

    fun findLatestLevel(account: AccountID): Level

    fun store(history: LevelHistory)
}

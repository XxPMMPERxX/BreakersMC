package jp.pmmper.breakersmc.domain.account

interface LevelHistoryRepository {
    fun find(account: AccountID): List<LevelHistory>

    fun store(history: LevelHistory)
}

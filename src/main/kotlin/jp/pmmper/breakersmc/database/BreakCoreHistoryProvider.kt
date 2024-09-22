package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.game.GameID
import jp.pmmper.breakersmc.history.BreakCoreHistory

interface BreakCoreHistoryProvider {
    fun find(account: AccountID): List<BreakCoreHistory>

    fun find(game: GameID): List<BreakCoreHistory>

    fun store(history: BreakCoreHistory)
}

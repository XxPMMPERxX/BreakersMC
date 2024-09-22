package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.game.GameID
import jp.pmmper.breakersmc.history.JoinGameHistory

interface JoinGameHistoryProvider {
    fun find(account: AccountID): List<JoinGameHistory>

    fun find(gameID: GameID): List<JoinGameHistory>

    fun store(history: JoinGameHistory)
}

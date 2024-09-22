package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.game.Game
import jp.pmmper.breakersmc.game.GameID

interface GameHistoryProvider {
    fun new(): GameID

    fun store(game: Game)
}

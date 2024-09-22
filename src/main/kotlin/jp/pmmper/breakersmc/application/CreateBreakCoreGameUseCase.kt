package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.database.GameHistoryProvider
import jp.pmmper.breakersmc.game.breakcore.BreakCoreGame

class CreateBreakCoreGameUseCase(private val provider: GameHistoryProvider) {
    fun execute(): BreakCoreGame {
        val id = provider.new()
        return BreakCoreGame(id)
    }
}

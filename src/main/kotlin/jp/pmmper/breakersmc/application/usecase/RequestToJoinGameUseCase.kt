package jp.pmmper.breakersmc.application.usecase

import jp.pmmper.breakersmc.model.game.Game
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

class RequestToJoinGameUseCase {
    fun execute(player: Player, game: Game) {
        Bukkit.getPluginManager().callEvent(player.requestToJoinGame(game))
    }
}

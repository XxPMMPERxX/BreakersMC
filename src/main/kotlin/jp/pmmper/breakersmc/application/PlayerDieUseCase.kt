package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.game.Game
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

/**
 * プレイヤー死亡
 *
 * @constructor Create empty Player die use case
 */
class PlayerDieUseCase {
    fun execute(player: Player, game: Game, message: String, killer: Player? = null) {
        Bukkit.getPluginManager().callEvent(player.die(game, message, killer))
    }
}

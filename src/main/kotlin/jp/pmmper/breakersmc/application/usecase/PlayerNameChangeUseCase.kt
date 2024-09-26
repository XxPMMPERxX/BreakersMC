package jp.pmmper.breakersmc.application.usecase

import jp.pmmper.breakersmc.model.player.Name
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

/**
 * プレイヤー名変更ユースケース
 */
class PlayerNameChangeUseCase {
    fun execute(player: Player, name: Name) {
        Bukkit.getPluginManager().callEvent(player.changeName(name))
    }
}

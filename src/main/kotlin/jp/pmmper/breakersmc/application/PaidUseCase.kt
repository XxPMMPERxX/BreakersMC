package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.Money
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

/**
 * 支払ユースケース
 */
class PaidUseCase {
    fun execute(player: Player, amount: Money) {
        Bukkit.getPluginManager().callEvent(player.pay(amount))
    }
}

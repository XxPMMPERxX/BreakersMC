package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.Money
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

/**
 * 請求
 */
class ChargeUseCase {
    fun execute(from: Player, to: Player, amount: Money) {
        Bukkit.getPluginManager().callEvent(from.charge(to, amount))
    }
}

package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.Money
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

/**
 * お金受取ユースケース
 */
class CreditedUseCase {
    fun execute(player: Player, amount: Money) {
        Bukkit.getPluginManager().callEvent(player.credited(amount))
        // 従来ならplayerRepositoryみたいなのでplayerを保存する。以下のような具合で
        // 今回はログありきのシステムなのでしないけど
        // val playerRepository: PlayerRepository
        // playerRepository.store(player)
    }
}

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
        // 仮にプレイヤーの所持金を保持するテーブルがあるならここで呼び出す。下のような具合で
        // 今回はログありきのシステムなのでしないけど
        // val moneyRepository: MoneyRepository
        // moneyRepository.store(player.money)
    }
}

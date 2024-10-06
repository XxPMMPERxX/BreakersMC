package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.player.event.PlayerCharged
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * 請求イベントリスナー
 */
class ChargedListener : Listener {
    @EventHandler
    fun listen(event: PlayerCharged) {
        val from = event.from
        val to = event.to
        val amount = event.amount

        // 支払ができない場合は処理終了
        try {
            Bukkit.getPluginManager().callEvent(to.pay(amount))
        } catch (e: IllegalArgumentException) {
            return
        }

        // 受取ができない場合は戻して処理終了（ログの保存はCreditedListener/PaidListenerに任せる）
        try {
            Bukkit.getPluginManager().callEvent(from.credited(amount))
        } catch (e: IllegalArgumentException) {
            to.credited(amount)
            return
        }
    }
}

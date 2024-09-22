package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.Charged
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * 請求イベントリスナー
 */
class ChargedListener : Listener {
    @EventHandler
    fun listen(event: Charged) {
        val from = event.from
        val to = event.to
        val amount = event.amount

        // 請求元が金額を受け取れる状態にない または 請求先が支払える状態にない 場合は処理終了
        if (!from.canCredited(amount) || !to.canPay(amount)) return

        // 請求先は支払う 請求元は受け取る（ログ保存処理等はPaidListener/CreditedListenerに任せる）
        Bukkit.getPluginManager().callEvent(to.pay(amount))
        Bukkit.getPluginManager().callEvent(from.credited(amount))
    }
}

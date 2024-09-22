package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.Paid
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * 支払イベントリスナー
 */
class PaidListener : Listener {
    @EventHandler
    fun listen(event: Paid) {
        TODO("ログの保存")
    }
}

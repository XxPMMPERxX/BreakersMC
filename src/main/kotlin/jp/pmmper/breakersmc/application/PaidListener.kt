package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.PlayerPaid
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

/**
 * 支払イベントリスナー
 */
class PaidListener : Listener {
    @EventHandler
    fun listen(event: PlayerPaid) {
        TODO("ログの保存")
    }
}

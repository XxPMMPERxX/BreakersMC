package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.Credited
import org.bukkit.event.EventHandler

/**
 * お金受取リスナー
 */
class CreditedListener {
    @EventHandler
    fun listen(event: Credited) {
        TODO("ログの保存")
    }
}

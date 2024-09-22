package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.PlayerCredited
import org.bukkit.event.EventHandler

/**
 * お金受取リスナー
 */
class CreditedListener {
    @EventHandler
    fun listen(event: PlayerCredited) {
        TODO("ログの保存")
    }
}

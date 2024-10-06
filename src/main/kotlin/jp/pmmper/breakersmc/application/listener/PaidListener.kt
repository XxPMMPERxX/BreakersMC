package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.player.event.PlayerPaid
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PaidListener : Listener {
    @EventHandler
    fun listen(event: PlayerPaid) {
        TODO("ログの保存")
    }
}

package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.PlayerCredited
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class CreditedListener : Listener {
    @EventHandler
    fun listen(event: PlayerCredited) {
        TODO("ログの保存")
    }
}

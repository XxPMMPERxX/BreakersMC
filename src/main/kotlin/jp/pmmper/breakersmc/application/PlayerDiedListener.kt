package jp.pmmper.breakersmc.application

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.PlayerDeathEvent

class PlayerDiedListener : Listener {
    @EventHandler
    fun listen(event: PlayerDeathEvent) {
        TODO("ログ保存")
    }
}

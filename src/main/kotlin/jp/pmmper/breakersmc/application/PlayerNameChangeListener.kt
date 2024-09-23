package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.event.PlayerNameChanged
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerNameChangeListener : Listener {
    @EventHandler
    fun listen(event: PlayerNameChanged) {
        TODO("ログの保存")
    }
}

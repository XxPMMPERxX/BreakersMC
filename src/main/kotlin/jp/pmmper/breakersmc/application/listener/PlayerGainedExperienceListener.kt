package jp.pmmper.breakersmc.application.listener

import jp.pmmper.breakersmc.model.player.event.PlayerGainedExperience
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class PlayerGainedExperienceListener : Listener {
    @EventHandler
    fun listen(event: PlayerGainedExperience) {
        TODO("ログ保存")
    }
}

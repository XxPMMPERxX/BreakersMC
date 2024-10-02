package jp.pmmper.breakersmc.application.usecase

import jp.pmmper.breakersmc.model.player.Experience
import jp.pmmper.breakersmc.model.player.Player
import org.bukkit.Bukkit

class PlayerGainExperienceUseCase {
    fun execute(player: Player, amount: Experience) {
        Bukkit.getPluginManager().callEvent(player.gainExperience(amount))
    }
}

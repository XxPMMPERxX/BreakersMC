package jp.pmmper.breakersmc.application

import jp.pmmper.breakersmc.model.player.Player
import jp.pmmper.breakersmc.model.team.Team
import org.bukkit.Bukkit

class RequestToJoinTeamUseCase {
    fun execute(player: Player, team: Team) {
        Bukkit.getPluginManager().callEvent(player.requestToJoinTeam(team))
    }
}

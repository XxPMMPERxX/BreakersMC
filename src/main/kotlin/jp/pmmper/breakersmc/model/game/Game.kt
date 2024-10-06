package jp.pmmper.breakersmc.model.game

import jp.pmmper.breakersmc.model.game.event.GamePhaseChanged
import jp.pmmper.breakersmc.model.game.event.PlayerJoinedGame
import jp.pmmper.breakersmc.model.player.Player
import jp.pmmper.breakersmc.model.player.event.PlayerRequestedToJoinGame
import jp.pmmper.breakersmc.model.team.Name
import jp.pmmper.breakersmc.model.team.Team
import jp.pmmper.breakersmc.model.team.TeamID
import jp.pmmper.breakersmc.model.team.event.PlayerJoinedTeam
import java.time.LocalDateTime
import kotlin.random.Random

abstract class Game(id: GameID) {
    val id: GameID = id
    var beginAt: LocalDateTime? = null
        protected set
    var endedAt: LocalDateTime? = null
        protected set
    var phase = Phase.CLOSE
        private set
    private val team1 = Team(TeamID(1), Name("RED"))
    private val team2 = Team(TeamID(2), Name("BLUE"))

    /**
     * 次フェーズに移行
     *
     * @return
     */
    fun nextPhase(): GamePhaseChanged {
        val nextPhase = when (phase) {
            Phase.CLOSE -> Phase.RECRUITING
            Phase.RECRUITING -> Phase.PREPARE
            Phase.PREPARE -> Phase.IN_GAME
            Phase.IN_GAME -> Phase.FINISHED
            Phase.FINISHED -> Phase.CLOSE
        }

        phase = nextPhase
        return GamePhaseChanged(this, nextPhase)
    }

    /**
     * プレイヤーがゲームに参加しているか
     *
     * @param player
     * @return
     */
    fun isPlayerJoined(player: Player): Boolean {
        return team1.isPlayerJoined(player) || team2.isPlayerJoined(player)
    }

    /**
     * プレイヤーの参加リクエストに応答する
     *
     * @param request
     * @return 参加できない場合はnull
     */
    fun respondJoinRequest(request: PlayerRequestedToJoinGame): Pair<PlayerJoinedGame, PlayerJoinedTeam>? {
        val player = request.player

        if (isPlayerJoined(player)) return null

        val count1 = team1.count()
        val count2 = team2.count()

        val team = when {
            count1 < count2 -> team1
            count1 > count2 -> team2
            else -> if (Random.nextBoolean()) team1 else team2
        }
        val result = team.respondJoinRequest(player.requestToJoinTeam(team)) ?: return null

        return Pair(PlayerJoinedGame(player, this), result)
    }
}

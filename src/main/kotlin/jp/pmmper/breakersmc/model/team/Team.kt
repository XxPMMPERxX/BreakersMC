package jp.pmmper.breakersmc.model.team

import jp.pmmper.breakersmc.model.player.Player
import jp.pmmper.breakersmc.model.player.event.PlayerRequestedToJoinTeam
import jp.pmmper.breakersmc.model.team.event.PlayerJoinedTeam

open class Team(id: TeamID, name: Name) {
    val id = id
    val name = name
    private val players = mutableListOf<Player>()

    /**
     * チームの全プレイヤーを取得
     *
     * @return
     */
    fun getPlayers(): List<Player> {
        return players.toList()
    }

    /**
     * プレイヤーがチームに参加しているか
     *
     * @param player
     * @return
     */
    fun isPlayerJoined(player: Player): Boolean {
        return players.contains(player)
    }

    /**
     * プレイヤーの参加リクエストに応答する
     *
     * @param request
     * @return 参加できない場合はnull
     */
    fun respondJoinRequest(request: PlayerRequestedToJoinTeam): PlayerJoinedTeam? {
        val player = request.player

        if (isPlayerJoined(player)) return null
        players.add(player)
        return PlayerJoinedTeam(player, this)
    }

    fun count(): Int {
        return players.count()
    }
}

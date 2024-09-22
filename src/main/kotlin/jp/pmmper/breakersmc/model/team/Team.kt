package jp.pmmper.breakersmc.model.team

import jp.pmmper.breakersmc.model.player.Player
import jp.pmmper.breakersmc.model.team.event.PlayerJoined

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
     * プレイヤーを参加させる
     *
     * @param player
     * @return
     */
    fun joinPlayer(player: Player): PlayerJoined {
        check(!isPlayerJoined(player))
        return PlayerJoined(player, this)
    }
}

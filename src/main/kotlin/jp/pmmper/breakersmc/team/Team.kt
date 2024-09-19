package jp.pmmper.breakersmc.team

import org.bukkit.entity.Player

class Team(id: TeamID, name: Name) {
    val id: TeamID = id
    val name: Name = name
    val players = mutableListOf<Player>()

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
    fun joinPlayer(player: Player): Boolean {
        if (!isPlayerJoined(player)) {
            return players.add(player)
        }
        return false
    }

    /**
     * プレイヤーを退出させる
     *
     * @param player
     * @return
     */
    fun kickPlayer(player: Player): Boolean {
        if (isPlayerJoined(player)) {
            return players.remove(player)
        }
        return false
    }
}

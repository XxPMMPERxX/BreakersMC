package jp.pmmper.breakersmc.team

import org.bukkit.entity.Player

open class Team(id: TeamID, name: Name) {
    val id: TeamID = id
    val name: Name = name
    private val players = mutableListOf<Player>()

    /**
     * 全プレイヤーを取得
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
    fun joinPlayer(player: Player): Boolean {
        if (isPlayerJoined(player)) return false
        return players.add(player)
    }

    /**
     * プレイヤーを退出させる
     *
     * @param player
     * @return
     */
    fun kickPlayer(player: Player): Boolean {
        if (!isPlayerJoined(player)) return false
        return players.remove(player)
    }

    /**
     * プレイヤー数を取得
     *
     * @return
     */
    fun count(): Int {
        return players.count()
    }
}

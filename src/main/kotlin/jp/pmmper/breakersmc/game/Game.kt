package jp.pmmper.breakersmc.game

import org.bukkit.entity.Player
import java.time.LocalDateTime

abstract class Game(
    val id: GameID,
    val players: MutableList<Player>,
    var beganAt: LocalDateTime?,
    var endedAt: LocalDateTime?
) {
    /**
     * プレイヤーがゲームに参加しているか
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
    fun addPlayer(player: Player): Boolean {
        if (!isPlayerJoined(player)) {
            players.add(player)
            return true
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

    /**
     * プレイヤー募集段階
     *
     * @return
     */
    abstract fun onRecruiting(): Boolean

    /**
     * ゲーム準備段階
     *
     * @return
     */
    abstract fun onPrepare(): Boolean

    /**
     * ゲーム開始時
     *
     * @return
     */
    abstract fun onBegin(): Boolean

    /**
     * ゲーム終了時
     *
     * @return
     */
    abstract fun onEnd(): Boolean
}

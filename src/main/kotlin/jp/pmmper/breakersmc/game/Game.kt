package jp.pmmper.breakersmc.game

import org.bukkit.entity.Player
import java.time.LocalDateTime

abstract class Game(id: GameID) {
    val id: GameID = id
    val players = mutableListOf<Player>()
    var beginAt: LocalDateTime? = null
        private set
    var endedAt: LocalDateTime? = null
        private set
    var phase = Phase.CLOSE
        private set

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

    /**
     * 次フェーズに移行
     *
     */
    fun next() {
        when (phase) {
            Phase.CLOSE -> {
                onRecruiting()
                phase = Phase.RECRUITING
            }

            Phase.RECRUITING -> {
                onPrepare()
                phase = Phase.PREPARE
            }

            Phase.PREPARE -> {
                onBegin()
                phase = Phase.IN_GAME
                beginAt = LocalDateTime.now()
            }

            Phase.IN_GAME -> {
                onEnd()
                phase = Phase.FINISHED
                endedAt = LocalDateTime.now()
            }

            Phase.FINISHED -> {
                onClose()
                phase = Phase.CLOSE
            }
        }
    }

    /**
     * プレイヤー募集段階
     *
     * @return
     */
    protected abstract fun onRecruiting(): Boolean

    /**
     * ゲーム準備段階
     *
     * @return
     */
    protected abstract fun onPrepare(): Boolean

    /**
     * ゲーム開始時
     *
     * @return
     */
    protected abstract fun onBegin(): Boolean

    /**
     * ゲーム終了時
     *
     * @return
     */
    protected abstract fun onEnd(): Boolean

    /**
     * ゲームセッション閉じる時
     *
     * @return
     */
    protected abstract fun onClose(): Boolean
}

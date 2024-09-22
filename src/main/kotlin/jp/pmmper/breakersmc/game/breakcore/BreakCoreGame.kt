package jp.pmmper.breakersmc.game.breakcore

import jp.pmmper.breakersmc.account.Account
import jp.pmmper.breakersmc.database.AccountProvider
import jp.pmmper.breakersmc.database.BreakCoreHistoryProvider
import jp.pmmper.breakersmc.database.GameHistoryProvider
import jp.pmmper.breakersmc.database.JoinGameHistoryProvider
import jp.pmmper.breakersmc.event.PhaseChangingEvent
import jp.pmmper.breakersmc.game.Game
import jp.pmmper.breakersmc.game.Phase
import jp.pmmper.breakersmc.history.BreakCoreHistory
import jp.pmmper.breakersmc.history.JoinGameHistory
import jp.pmmper.breakersmc.team.HitPoint
import jp.pmmper.breakersmc.team.Name
import jp.pmmper.breakersmc.team.TeamID
import jp.pmmper.breakersmc.team.TeamWithHP
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.block.Block
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import java.time.LocalDateTime
import kotlin.random.Random

/**
 * 要リファクタリング
 */
class BreakCoreGame(
    private val accountProvider: AccountProvider,
    private val gameProvider: GameHistoryProvider,
    private val joinProvider: JoinGameHistoryProvider,
    private val breakProvider: BreakCoreHistoryProvider
) : Game(gameProvider.new()), Listener {
    val team1 = TeamWithHP(TeamID(1), Name("RED"), HitPoint(4))
    val team2 = TeamWithHP(TeamID(2), Name("BLUE"), HitPoint(4))

    /**
     * ゲームに参加しているか
     *
     * @param account
     * @return
     */
    fun isAccountJoined(account: Account): Boolean {
        return team1.isAccountJoined(account) || team2.isAccountJoined(account)
    }

    /**
     * コアブロックを壊せるか
     *
     * @param block
     * @param account
     * @return
     */
    fun canBreakCore(block: Block, account: Account): Boolean {
        return (team1.isAccountJoined(account) && block.type == Material.RED_TERRACOTTA) ||
                (team2.isAccountJoined(account) && block.type == Material.BLUE_TERRACOTTA)
    }

    /**
     * ゲームに参加
     *
     * @param account
     * @return
     */
    fun joinAccount(account: Account): TeamID? {
        if (isAccountJoined(account)) return null

        val count1 = team1.count()
        val count2 = team2.count()

        // 人数が少ない方のチームを取得 人数が同じ場合はランダム
        val team = when {
            count1 < count2 -> team1
            count1 > count2 -> team2
            else -> if (Random.nextBoolean()) team1 else team2
        }

        if (!team.joinAccount(account)) return null

        // 保存
        joinProvider.store(JoinGameHistory(account.id, id, team.id))

        return team.id
    }

    /**
     * HPを1減らす
     *
     * @param breaker
     * @param broke
     * @return
     */
    fun decreaseHitPoint(breaker: Account, broke: TeamID): Boolean {
        val team = when (broke) {
            team1.id -> team1
            team2.id -> team2
            else -> return false
        }

        // 破壊されたチームと破壊者のチームが同じ場合は無効
        if (team.joinAccount(breaker)) return false

        try {
            team.decreaseHitPoint(HitPoint(1))

            // 保存
            breakProvider.store(BreakCoreHistory(breaker.id, id))
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }

    @EventHandler
    fun onStartGame(event: PhaseChangingEvent) {
        // 遷移後フェーズがゲーム中でない場合は処理中止
        if (event.nextPhase != Phase.IN_GAME) return

        beginAt = LocalDateTime.now()
        Bukkit.broadcast(Component.text("ゲーム開始"))
    }

    @EventHandler
    fun onBreakCore(event: BlockBreakEvent) {
        val block = event.block
        val player = event.player

        val accountID = accountProvider.findIDByUUID(player.uniqueId)

        // コアを壊せたか
        val isBreakRed = block.type == Material.RED_TERRACOTTA && team1.isAccountJoined(accountID)
        val isBreakBlue = block.type == Material.BLUE_TERRACOTTA && team2.isAccountJoined(accountID)

        // コア破壊でない場合は処理中止
        if (!isBreakRed && !isBreakBlue) return

        when {
            isBreakRed -> {
                team1.decreaseHitPoint(HitPoint(1))
            }
        }

        // コア(ブロック)破壊を許可
        event.isCancelled = false

        Bukkit.broadcast(Component.text("${player.name}が"))
    }
}

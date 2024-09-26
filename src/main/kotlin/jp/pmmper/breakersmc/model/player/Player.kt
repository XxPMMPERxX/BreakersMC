package jp.pmmper.breakersmc.model.player

import jp.pmmper.breakersmc.model.game.Game
import jp.pmmper.breakersmc.model.player.event.*
import jp.pmmper.breakersmc.model.team.Team
import java.util.*

class Player(id: PlayerID, uuid: UUID, name: Name, money: Money, level: Level) {
    val id = id
    val uuid = uuid
    var name = name
        private set
    var money = money
        private set
    var level = level
        private set

    /**
     * 名前を変更する
     *
     * @param name
     * @return
     */
    fun changeName(name: Name): PlayerNameChanged {
        this.name = name
        return PlayerNameChanged(this, name)
    }

    /**
     * 請求する
     *
     * @param to     請求先
     * @param amount 金額
     * @return
     */
    fun charge(to: Player, amount: Money): PlayerCharged {
        return PlayerCharged(this, to, amount)
    }

    /**
     * お金を受け取る
     *
     * @param amount
     * @return
     */
    fun credited(amount: Money): PlayerCredited {
        money = Money(money.value + amount.value)
        return PlayerCredited(this, amount)
    }

    /**
     * お金を支払う
     *
     * @param amount 金額
     * @return
     */
    fun pay(amount: Money): PlayerPaid {
        money = Money(money.value - amount.value)
        return PlayerPaid(this, amount)
    }

    /**
     * お金を支払えるか
     *
     * @param amount
     * @return
     */
    fun canPay(amount: Money): Boolean {
        return runCatching { Money(money.value - amount.value) }.isSuccess
    }

    /**
     * 死
     *
     * @param game
     * @param message
     * @param killer
     * @return
     */
    fun die(game: Game, message: String, killer: Player?): PlayerDied {
        return PlayerDied(this, game, message, killer)
    }

    /**
     * チームに参加リクエストを送る
     *
     * @param team
     * @return
     */
    fun requestToJoinTeam(team: Team): PlayerRequestedToJoinTeam {
        return PlayerRequestedToJoinTeam(this, team)
    }
}

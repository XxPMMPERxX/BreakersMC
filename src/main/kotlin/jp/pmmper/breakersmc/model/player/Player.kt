package jp.pmmper.breakersmc.model.player

import jp.pmmper.breakersmc.model.player.event.Charged
import jp.pmmper.breakersmc.model.player.event.Credited
import jp.pmmper.breakersmc.model.player.event.Paid
import java.util.*

class Player(id: PlayerID, uuid: UUID, name: Name, money: Money, level: Level) {
    val id = id
    val uuid = uuid
    var name = name
    var money = money
        private set
    var level = level
        private set

    /**
     * 請求する
     *
     * @param to     請求先
     * @param amount 金額
     * @return
     */
    fun charge(to: Player, amount: Money): Charged {
        return Charged(this, to, amount)
    }

    /**
     * お金を受け取る
     *
     * @param amount
     */
    fun credited(amount: Money): Credited {
        check(canCredited(amount))
        money = Money(money.value + amount.value)
        return Credited(this, amount)
    }

    /**
     * お金を支払う
     *
     * @param amount 金額
     */
    fun pay(amount: Money): Paid {
        check(canPay(amount))
        money = Money(money.value - amount.value)
        return Paid(this, amount)
    }

    /**
     * 指定した金額を受け取れるか
     *
     * @param amount 金額
     * @return
     */
    fun canCredited(amount: Money): Boolean {
        try {
            Money(money.value + amount.value)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }

    /**
     * 指定した金額を支払えるか
     *
     * @param amount
     * @return
     */
    fun canPay(amount: Money): Boolean {
        try {
            Money(money.value - amount.value)
        } catch (e: IllegalArgumentException) {
            return false
        }
        return true
    }
}

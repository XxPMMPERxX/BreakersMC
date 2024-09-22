package jp.pmmper.breakersmc.team

/**
 * チームクラスにHitPointの概念を付け足したクラス
 * どういう命名が適切だろうか。。。
 *
 * @constructor Create empty Team with h p
 */
class TeamWithHP(id: TeamID, name: Name, point: HitPoint) : Team(id, name) {
    var point = point
        private set

    fun increase(amount: HitPoint) {
        point = HitPoint(point.value + amount.value)
    }

    fun decrease(amount: HitPoint) {
        point = HitPoint(point.value - amount.value)
    }
}

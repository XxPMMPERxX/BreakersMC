package jp.pmmper.breakersmc.account

import java.util.*

class Account(
    val id: AccountID,
    val uuid: UUID,
    var name: Name,
    var money: Money,
    var level: Level,
    var killCount: Int,
    var deathCount: Int
)

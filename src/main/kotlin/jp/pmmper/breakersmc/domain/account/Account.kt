package jp.pmmper.breakersmc.domain.account

import java.util.*

class Account(
    val id: AccountID,
    val uuid: UUID,
    var name: Name,
    var money: Money,
    var level: Level
) {
}

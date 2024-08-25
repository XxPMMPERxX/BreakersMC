package jp.pmmper.breakersmc.domain.account

import jp.pmmper.breakersmc.domain.Reason
import java.time.ZonedDateTime

data class MoneyHistory(
    val account: AccountID,
    val oldMoney: Money,
    val newMoney: Money,
    val reason: Reason,
    val datetime: ZonedDateTime
)

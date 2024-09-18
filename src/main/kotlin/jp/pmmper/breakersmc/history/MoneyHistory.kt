package jp.pmmper.breakersmc.history

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Money
import java.time.LocalDateTime

data class MoneyHistory(
    val account: AccountID,
    val oldMoney: Money,
    val newMoney: Money,
    val reason: Reason?,
    val datetime: LocalDateTime
)

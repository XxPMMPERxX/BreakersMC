package jp.pmmper.breakersmc.history

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Name
import java.time.LocalDateTime

data class NameHistory(val account: AccountID, val oldName: Name, val newName: Name, val datetime: LocalDateTime)

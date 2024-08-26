package jp.pmmper.breakersmc.domain.account

import java.time.LocalDateTime

data class NameHistory(val account: AccountID, val oldName: Name, val newName: Name, val datetime: LocalDateTime)

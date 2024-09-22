package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.Account
import jp.pmmper.breakersmc.account.AccountID
import java.util.*

interface AccountProvider {
    fun find(id: AccountID): Account?

    fun findIDByUUID(uuid: UUID): AccountID?
}

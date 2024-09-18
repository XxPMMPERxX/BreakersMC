package jp.pmmper.breakersmc.database

import jp.pmmper.breakersmc.account.Account
import jp.pmmper.breakersmc.account.AccountID

interface AccountProvider {
    fun find(id: AccountID): Account?
}

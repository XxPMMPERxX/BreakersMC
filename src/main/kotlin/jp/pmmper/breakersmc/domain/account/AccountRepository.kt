package jp.pmmper.breakersmc.domain.account

interface AccountRepository {
    fun find(id: AccountID): Account?
}

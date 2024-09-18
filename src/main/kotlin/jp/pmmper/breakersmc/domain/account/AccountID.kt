package jp.pmmper.breakersmc.domain.account

data class AccountID(val value: Int) {
    init {
        require(value >= 0)
    }
}

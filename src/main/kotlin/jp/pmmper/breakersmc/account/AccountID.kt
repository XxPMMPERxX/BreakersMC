package jp.pmmper.breakersmc.account

data class AccountID(val value: Int) {
    init {
        require(value >= 0)
    }
}

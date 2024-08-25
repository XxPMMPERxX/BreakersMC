package jp.pmmper.breakersmc.domain.account

data class Name(val value: String) {
    init {
        require(value.length in 1..16)
    }
}

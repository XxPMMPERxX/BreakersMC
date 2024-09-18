package jp.pmmper.breakersmc.account

data class Name(val value: String) {
    init {
        require(value.length in 1..16)
    }
}

package jp.pmmper.breakersmc.domain

data class Reason(val value: String) {
    init {
        require(value.length < 100)
    }
}

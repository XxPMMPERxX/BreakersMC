package jp.pmmper.breakersmc.history

data class Reason(val value: String) {
    init {
        require(value.length < 100)
    }
}

package jp.pmmper.breakersmc.model.player

data class Name(val value: String) {
    init {
        require(value.length in 1..16)
    }
}

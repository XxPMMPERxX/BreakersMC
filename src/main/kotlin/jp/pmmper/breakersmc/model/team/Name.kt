package jp.pmmper.breakersmc.model.team

data class Name(val value: String) {
    init {
        require(value.length in 1..10)
    }
}

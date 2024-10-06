package jp.pmmper.breakersmc.model.player

data class PlayerID(val value: Int) {
    init {
        require(value >= 0)
    }
}

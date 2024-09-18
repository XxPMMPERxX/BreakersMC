package jp.pmmper.breakersmc.game

data class GameID(val value: Int) {
    init {
        require(value >= 0)
    }
}

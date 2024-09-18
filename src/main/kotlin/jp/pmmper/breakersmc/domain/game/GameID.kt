package jp.pmmper.breakersmc.domain.game

data class GameID(val value: Int) {
    init {
        require(value >= 0)
    }
}

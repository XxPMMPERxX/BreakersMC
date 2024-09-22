package jp.pmmper.breakersmc.model.game

data class GameID(val value: Int) {
    init {
        require(value >= 0)
    }
}

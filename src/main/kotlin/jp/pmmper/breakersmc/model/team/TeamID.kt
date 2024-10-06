package jp.pmmper.breakersmc.model.team

data class TeamID(val value: Int) {
    init {
        require(value >= 0)
    }
}

package jp.pmmper.breakersmc.team

data class TeamID(val value: Int) {
    init {
        require(value >= 0)
    }
}


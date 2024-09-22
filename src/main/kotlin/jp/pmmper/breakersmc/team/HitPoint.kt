package jp.pmmper.breakersmc.team

data class HitPoint(val value: Int) {
    init {
        require(value <= 0)
    }
}

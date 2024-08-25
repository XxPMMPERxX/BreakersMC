package jp.pmmper.breakersmc.domain.account

data class Level(val value: Double) {
    companion object {
        const val MIN_VALUE = 0.0
        const val MAX_VALUE = 999.0
    }

    init {
        require(value in MIN_VALUE..MAX_VALUE)
    }
}

package jp.pmmper.breakersmc.model.player

data class Experience(val value: Double) {
    companion object {
        const val MIN_VALUE = 0.0
        const val MAX_VALUE = 999.0
    }

    init {
        require(value in MIN_VALUE..MAX_VALUE)
    }
}

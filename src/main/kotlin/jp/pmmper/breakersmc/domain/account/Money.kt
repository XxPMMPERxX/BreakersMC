package jp.pmmper.breakersmc.domain.account

data class Money(val value: UInt) {
    companion object {
        const val MIN_VALUE = 0u
        const val MAX_VALUE = 999999999u
    }

    init {
        require(value in MIN_VALUE..MAX_VALUE)
    }
}

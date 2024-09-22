package jp.pmmper.breakersmc.team

import jp.pmmper.breakersmc.account.Account

open class Team(id: TeamID, name: Name) {
    val id: TeamID = id
    val name: Name = name
    private val accounts = mutableListOf<Account>()

    /**
     * 全アカウントを取得
     *
     * @return
     */
    fun getPlayers(): List<Account> {
        return accounts.toList()
    }

    /**
     * アカウントがチームに参加しているか
     *
     * @param account
     * @return
     */
    fun isPlayerJoined(account: Account): Boolean {
        return accounts.contains(account)
    }

    /**
     * チームにアカウントを参加させる
     *
     * @param account
     * @return
     */
    fun joinPlayer(account: Account): Boolean {
        if (isPlayerJoined(account)) return false
        return accounts.add(account)
    }

    /**
     * チームからアカウントを退出させる
     *
     * @param account
     * @return
     */
    fun kickPlayer(account: Account): Boolean {
        if (!isPlayerJoined(account)) return false
        return accounts.remove(account)
    }

    /**
     * チームのアカウント数を取得
     *
     * @return
     */
    fun count(): Int {
        return accounts.count()
    }
}

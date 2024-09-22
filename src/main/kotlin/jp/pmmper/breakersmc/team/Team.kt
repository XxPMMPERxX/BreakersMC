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
    fun getAccounts(): List<Account> {
        return accounts.toList()
    }

    /**
     * アカウントがチームに参加しているか
     *
     * @param account
     * @return
     */
    fun isAccountJoined(account: Account): Boolean {
        return accounts.contains(account)
    }

    /**
     * チームにアカウントを参加させる
     *
     * @param account
     * @return
     */
    fun joinAccount(account: Account): Boolean {
        if (isAccountJoined(account)) return false
        return accounts.add(account)
    }

    /**
     * チームからアカウントを退出させる
     *
     * @param account
     * @return
     */
    fun kickAccount(account: Account): Boolean {
        if (!isAccountJoined(account)) return false
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

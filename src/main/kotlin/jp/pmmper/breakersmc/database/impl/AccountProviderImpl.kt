package jp.pmmper.breakersmc.database.impl

import jp.pmmper.breakersmc.account.*
import jp.pmmper.breakersmc.database.AccountProvider
import java.sql.Connection
import java.util.*

class AccountProviderImpl(private val connection: Connection) : AccountProvider {
    companion object {
        private const val SELECT_ID_QUERY = """
            SELECT id FROM accounts WHERE uuid = ?
        """

        private const val SELECT_QUERY = """
            SELECT
                player.id                AS id,
                player.uuid              AS uuid,
                latest_name.name         AS name,
                latest_money.money       AS money,
                latest_level.level       AS level,
                COUNT(DISTINCT death.id) AS death_count,
                COUNT(DISTINCT kill.id)  AS kill_count
            FROM                         AS accounts
            
            -- プレイヤーの最新の名前を取得
            INNER JOIN (
                SELECT
                    name.account    AS account,
                    name.new_name   AS name
                FROM name_histories AS name
                WHERE name.account = accounts.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_name
            
            -- プレイヤーの最新の所持金を取得
            INNER JOIN (
                SELECT
                    money.account    AS account,
                    money.new_money  AS money
                FROM money_histories AS money
                WHERE money.account = accounts.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_money
            
            -- プレイヤーの最新のレベルを取得
            INNER JOIN (
                SELECT
                    level.account    AS account,
                    level.new_level  AS level
                FROM level_histories AS level
                WHERE level.account = accounts.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_level
            
            INNER JOIN death_histories AS death ON death.dead  = accounts.id
            INNER JOIN death_histories AS kill  ON kill.killer = accounts.id
            
            WHERE accounts.id = ?
            LIMIT 1;
        """
    }

    override fun find(id: AccountID): Account? {
        var account: Account? = null

        val stmt = connection.prepareStatement(SELECT_QUERY)
        stmt.use {
            stmt.setInt(1, id.value)
            val result = stmt.executeQuery()
            if (result.first()) {
                account = Account(
                    AccountID(result.getInt("id")),
                    UUID.fromString(result.getString("uuid")),
                    Name(result.getString("name")),
                    Money(result.getInt("money")),
                    Level(result.getDouble("level")),
                    result.getInt("kill_count"),
                    result.getInt("death_count")
                )
            }
        }

        return account
    }

    override fun findIDByUUID(uuid: UUID): AccountID? {
        val stmt = connection.prepareStatement(SELECT_ID_QUERY)
        stmt.use {
            stmt.setObject(1, uuid)
            val result = stmt.executeQuery()
            if (result.first()) {
                return AccountID(result.getInt("id"))
            }
        }
        return null
    }
}

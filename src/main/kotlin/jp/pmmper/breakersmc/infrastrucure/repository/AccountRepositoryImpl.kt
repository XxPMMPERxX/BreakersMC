package jp.pmmper.breakersmc.infrastrucure.repository

import jp.pmmper.breakersmc.domain.account.*
import java.sql.Connection
import java.util.*

class AccountRepositoryImpl(private val connection: Connection) : AccountRepository {
    companion object {
        private const val SELECT_QUERY = """
            SELECT
                player.id                AS id,
                player.uuid              AS uuid,
                latest_name.name         AS name,
                latest_money.money       AS money,
                latest_level.level       AS level,
                COUNT(DISTINCT death.id) AS death_count,
                COUNT(DISTINCT kill.id)  AS kill_count
            FROM                         AS player
            
            -- プレイヤーの最新の名前を取得
            INNER JOIN (
                SELECT
                    name.player   AS player,
                    name.new_name AS name
                FROM name_histories AS name
                WHERE name.player = player.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_name
            
            -- プレイヤーの最新の所持金を取得
            INNER JOIN (
                SELECT
                    money.player    AS player,
                    money.new_money AS money
                FROM money_histories AS money
                WHERE money.player = player.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_money
            
            -- プレイヤーの最新のレベルを取得
            INNER JOIN (
                SELECT
                    level.player    AS player,
                    level.new_level AS level
                FROM level_histories AS level
                WHERE level.player = player.id
                ORDER BY id DESC
                LIMIT 1
            ) AS latest_level
            
            INNER JOIN death_histories AS death ON death.dead  = player.id
            INNER JOIN death_histories AS kill  ON kill.killer = player.id
            
            WHERE player.id = ?
            LIMIT 1;
        """
    }

    override fun find(id: AccountID): Account? {
        var account: Account?

        val stmt = connection.prepareStatement(SELECT_QUERY)
        stmt.use {
            stmt.setInt(1, id.value.toInt())
            val result = stmt.executeQuery()
            result.first()
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

        return account
    }
}

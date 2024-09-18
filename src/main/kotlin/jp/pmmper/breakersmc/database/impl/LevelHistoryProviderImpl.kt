package jp.pmmper.breakersmc.database.impl

import jp.pmmper.breakersmc.account.AccountID
import jp.pmmper.breakersmc.account.Level
import jp.pmmper.breakersmc.database.LevelHistoryProvider
import jp.pmmper.breakersmc.history.LevelHistory
import jp.pmmper.breakersmc.history.Reason
import java.sql.Connection

class LevelHistoryProviderImpl(private val connection: Connection) : LevelHistoryProvider {
    companion object {
        private const val SELECT_QUERY = """
            SELECT * FROM level_histories
            WHERE player = ?
        """

        private const val SELECT_LATEST_LEVEL_QUERY = """
            SELECT new_level FROM level_histories
            WHERE player = ?
            ORDER BY id DESC
            LIMIT 1
        """

        private const val INSERT_QUERY = """
            INSERT INTO level_histories (player, old_level, new_level, reason) VALUES
            (?, ?, ?, ?)
        """
    }

    override fun find(account: AccountID): List<LevelHistory> {
        val list = mutableListOf<LevelHistory>()

        val stmt = connection.prepareStatement(SELECT_QUERY)
        stmt.use {
            stmt.setInt(1, account.value)
            val result = stmt.executeQuery()

            while (result.next()) {
                list.add(
                    LevelHistory(
                        AccountID(result.getInt("player")),
                        Level(result.getDouble("old_level")),
                        Level(result.getDouble("new_level")),
                        Reason(result.getString("reason")),
                        result.getTimestamp("created_at").toLocalDateTime()
                    )
                )
            }
        }

        return list
    }

    override fun findLatestLevel(account: AccountID): Level {
        val stmt = connection.prepareStatement(SELECT_LATEST_LEVEL_QUERY)
        stmt.use {
            val result = stmt.executeQuery()
            result.first()
            return Level(result.getDouble("new_level"))
        }
    }

    override fun store(history: LevelHistory) {
        val stmt = connection.prepareStatement(INSERT_QUERY)
        stmt.use {
            stmt.setInt(1, history.account.value)
            stmt.setDouble(2, history.oldLevel.value)
            stmt.setDouble(3, history.newLevel.value)
            stmt.setString(4, history.reason?.value)

            stmt.execute()
        }
    }
}

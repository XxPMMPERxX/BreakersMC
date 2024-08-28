package jp.pmmper.breakersmc.infrastrucure.repository

import jp.pmmper.breakersmc.domain.Reason
import jp.pmmper.breakersmc.domain.account.AccountID
import jp.pmmper.breakersmc.domain.account.Level
import jp.pmmper.breakersmc.domain.account.LevelHistory
import jp.pmmper.breakersmc.domain.account.LevelHistoryRepository
import java.sql.Connection

class LevelHistoryRepositoryImpl(private val connection: Connection) : LevelHistoryRepository {
    companion object {
        private const val SELECT_QUERY = """
            SELECT * FROM level_histories
            WHERE player = ?
        """

        private const val LATEST_LEVEL_QUERY = """
            SELECT level FROM level_histories
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
        val stmt = connection.prepareStatement(LATEST_LEVEL_QUERY)
        stmt.use {
            val result = stmt.executeQuery()
            result.first()
            return Level(result.getDouble("level"))
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

package jp.pmmper.breakersmc.infrastrucure.repository

import jp.pmmper.breakersmc.domain.Reason
import jp.pmmper.breakersmc.domain.account.AccountID
import jp.pmmper.breakersmc.domain.account.DeathHistory
import jp.pmmper.breakersmc.domain.account.DeathHistoryRepository
import jp.pmmper.breakersmc.domain.game.GameID
import java.sql.Connection
import java.sql.Types

class DeathRepositoryImpl(private val connection: Connection) : DeathHistoryRepository {
    companion object {
        private const val SELECT_DEATH_QUERY = """
            SELECT * FROM death_histories
            WHERE killer = ?
        """

        private const val SELECT_KILL_QUERY = """
            SELECT * FROM death_histories
            WHERE dead = ?
        """

        private const val INSERT_QUERY = """
            INSERT INTO death_histories (dead, game, message, killer) VALUES
            (?, ?, ?, ?)
        """
    }

    override fun findDeathHistory(account: AccountID): List<DeathHistory> {
        val list = mutableListOf<DeathHistory>()

        val stmt = connection.prepareStatement(SELECT_DEATH_QUERY)
        stmt.use {
            stmt.setInt(1, account.value.toInt())
            val result = stmt.executeQuery()

            while (result.next()) {
                list.add(
                    DeathHistory(
                        AccountID(result.getInt("dead").toUInt()),
                        GameID(result.getInt("game").toUInt()),
                        Reason(result.getString("message")),
                        if (result.getInt("killer") != 0) AccountID(result.getInt("killer").toUInt()) else null,
                        result.getTimestamp("created_at").toLocalDateTime()
                    )
                )
            }
        }

        return list
    }

    override fun findKillHistory(account: AccountID): List<DeathHistory> {
        val list = mutableListOf<DeathHistory>()

        val stmt = connection.prepareStatement(SELECT_KILL_QUERY)
        stmt.use {
            stmt.setInt(1, account.value.toInt())
            val result = stmt.executeQuery()

            while (result.next()) {
                list.add(
                    DeathHistory(
                        AccountID(result.getInt("dead").toUInt()),
                        GameID(result.getInt("game").toUInt()),
                        Reason(result.getString("message")),
                        if (result.getInt("killer") != 0) AccountID(result.getInt("killer").toUInt()) else null,
                        result.getTimestamp("created_at").toLocalDateTime()
                    )
                )
            }
        }

        return list
    }

    override fun store(history: DeathHistory) {
        val stmt = connection.prepareStatement(INSERT_QUERY)
        stmt.use {
            stmt.setInt(1, history.dead.value.toInt())
            stmt.setInt(2, history.game.value.toInt())
            stmt.setString(3, history.message.value)
            if (history.killer != null) stmt.setInt(4, history.killer.value.toInt()) else stmt.setNull(4, Types.NULL)

            stmt.execute()
        }
    }
}

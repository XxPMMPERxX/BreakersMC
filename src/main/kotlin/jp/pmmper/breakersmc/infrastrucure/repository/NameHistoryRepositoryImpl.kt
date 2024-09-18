package jp.pmmper.breakersmc.infrastrucure.repository

import jp.pmmper.breakersmc.domain.account.AccountID
import jp.pmmper.breakersmc.domain.account.Name
import jp.pmmper.breakersmc.domain.account.NameHistory
import jp.pmmper.breakersmc.domain.account.NameHistoryRepository
import java.sql.Connection

class NameHistoryRepositoryImpl(private val connection: Connection) : NameHistoryRepository {
    companion object {
        private const val SELECT_QUERY = """
            SELECT * FROM name_histories
            WHERE player = ?
        """

        private const val LATEST_NAME_QUERY = """
            SELECT new_name FROM name_histories
            WHERE player = ?
            ORDER BY id DESC
            LIMIT 1
        """

        private const val INSERT_QUERY = """
            INSERT INTO name_histories (player, old_name, new_name) VALUES
            (?, ?, ?)
        """
    }

    override fun find(account: AccountID): List<NameHistory> {
        val list = mutableListOf<NameHistory>()

        val stmt = connection.prepareStatement(SELECT_QUERY)
        stmt.use {
            stmt.setInt(1, account.value)
            val result = stmt.executeQuery()

            while (result.next()) {
                list.add(
                    NameHistory(
                        AccountID(result.getInt("player")),
                        Name(result.getString("old_name")),
                        Name(result.getString("new_name")),
                        result.getTimestamp("created_at").toLocalDateTime()
                    )
                )
            }
        }

        return list
    }

    override fun findLatestName(account: AccountID): Name {
        val stmt = connection.prepareStatement(LATEST_NAME_QUERY)
        stmt.use {
            val result = stmt.executeQuery()
            result.first()
            return Name(result.getString("new_name"))
        }
    }

    override fun store(history: NameHistory) {
        val stmt = connection.prepareStatement(INSERT_QUERY)
        stmt.use {
            stmt.setInt(1, history.account.value)
            stmt.setString(2, history.oldName.value)
            stmt.setString(3, history.newName.value)

            stmt.execute()
        }
    }
}

package jp.pmmper.breakersmc.infrastrucure.repository

import jp.pmmper.breakersmc.domain.Reason
import jp.pmmper.breakersmc.domain.account.AccountID
import jp.pmmper.breakersmc.domain.account.Money
import jp.pmmper.breakersmc.domain.account.MoneyHistory
import jp.pmmper.breakersmc.domain.account.MoneyHistoryRepository
import java.sql.Connection

class MoneyHistoryRepositoryImpl(private val connection: Connection) : MoneyHistoryRepository {
    companion object {
        private const val SELECT_QUERY = """
            SELECT * FROM money_histories
            WHERE player = ?
        """

        private const val LATEST_MONEY_QUERY = """
            SELECT money FROM money_histories
            WHERE player = ?
            ORDER BY id DESC
            LIMIT 1
        """

        private const val INSERT_QUERY = """
            INSERT INTO money_histories (player, old_money, new_money, reason) VALUES
            (?, ?, ?, ?)
        """
    }

    override fun find(account: AccountID): List<MoneyHistory> {
        val list = mutableListOf<MoneyHistory>()

        val stmt = connection.prepareStatement(SELECT_QUERY)
        stmt.use {
            stmt.setInt(1, account.value)
            val result = stmt.executeQuery()

            while (result.next()) {
                list.add(
                    MoneyHistory(
                        AccountID(result.getInt("player")),
                        Money(result.getInt("old_money")),
                        Money(result.getInt("new_money")),
                        Reason(result.getString("reason")),
                        result.getTimestamp("created_at").toLocalDateTime()
                    )
                )
            }
        }

        return list
    }

    override fun findLatestMoney(account: AccountID): Money {
        val stmt = connection.prepareStatement(LATEST_MONEY_QUERY)
        stmt.use {
            val result = stmt.executeQuery()
            result.first()
            return Money(result.getInt("money"))
        }
    }

    override fun store(history: MoneyHistory) {
        val stmt = connection.prepareStatement(INSERT_QUERY)
        stmt.use {
            stmt.setInt(1, history.account.value)
            stmt.setInt(2, history.oldMoney.value)
            stmt.setInt(3, history.newMoney.value)
            stmt.setString(4, history.reason?.value)

            stmt.execute()
        }
    }
}

package jp.pmmper.breakersmc.database

import java.sql.Connection
import java.sql.DriverManager

object Database {
    var url: String? = null
    var id: String? = null
    var password: String? = null
    private var connection: Connection? = null

    fun connect(): Connection {
        if (connection == null || connection?.isClosed == true) {
            connection = DriverManager.getConnection(url, id, password)
        }
        return connection as Connection
    }

    fun beginTransaction() {
        connection?.autoCommit = false
    }

    fun commit() {
        connection?.commit()
        connection?.autoCommit = true
    }

    fun rollback() {
        connection?.rollback()
        connection?.autoCommit = true
    }
}

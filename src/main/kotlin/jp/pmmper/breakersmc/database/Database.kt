package jp.pmmper.breakersmc.database

import java.sql.Connection
import java.sql.DriverManager

object Database {
    var url: String? = null
        private set
    var id: String? = null
        private set
    var password: String? = null
        private set
    private var connection: Connection? = null

    fun init(url: String, id: String, password: String) {
        connection?.close()
        connection = null

        this.url = url
        this.id = id
        this.password = password
    }

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

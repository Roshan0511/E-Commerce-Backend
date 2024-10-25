package example.com.plugins

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import example.com.database.tables.TempVendor
import example.com.database.tables.Vendors
import io.ktor.server.application.Application
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabases() {
    Database.connect(dataSource())
    transaction {
        SchemaUtils.create(Vendors)
        SchemaUtils.create(TempVendor)
    }
}


private fun Application.dataSource() : HikariDataSource {
    val config = HikariConfig()
    config.driverClassName = environment.config.property("database.driverClassName").getString()
    config.jdbcUrl = environment.config.property("database.jdbcUrl").getString()
    config.username = environment.config.property("database.username").getString()
    config.password = environment.config.property("database.password").getString()
    config.maximumPoolSize = 10
    config.isAutoCommit = false
    config.transactionIsolation = environment.config.property("database.transactionIsolation").getString()
    config.validate()

    return HikariDataSource(config)
}
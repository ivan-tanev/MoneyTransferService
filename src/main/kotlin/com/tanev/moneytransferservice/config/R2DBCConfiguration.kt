package com.tanev.moneytransferservice.config

import io.r2dbc.postgresql.PostgresqlConnectionFactoryProvider
import io.r2dbc.spi.ConnectionFactories
import io.r2dbc.spi.ConnectionFactory
import io.r2dbc.spi.ConnectionFactoryOptions
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.r2dbc.connection.R2dbcTransactionManager
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.transaction.ReactiveTransactionManager

@Configuration
class R2DBCConfiguration(
    @Value("\${spring.r2dbc.username}") private val dbUser: String,
    @Value("\${spring.r2dbc.password}") private val dbPassword: String,
    @Value("\${spring.r2dbc.name}") private val dbName: String,
    @Value("\${spring.r2dbc.port}") private val dbPort: Int,
    @Value("\${spring.r2dbc.db.schema}") private val dbSchema: String,
) {

    @Bean
    fun databaseClient(connectionFactory: ConnectionFactory): DatabaseClient = DatabaseClient.builder()
        .connectionFactory(connectionFactory)
        .build()

    @Bean
    @Primary
    fun connectionFactory(): ConnectionFactory = ConnectionFactories.get(
        ConnectionFactoryOptions.builder()
            .option(ConnectionFactoryOptions.PROTOCOL, "postgresql")
            .option(ConnectionFactoryOptions.DRIVER, "pool")
            .option(ConnectionFactoryOptions.HOST, dbUser)
            .option(ConnectionFactoryOptions.USER, dbUser)
            .option(ConnectionFactoryOptions.PASSWORD, dbPassword)
            .option(ConnectionFactoryOptions.DATABASE, dbName)
            .option(ConnectionFactoryOptions.PORT, dbPort)
            .option(PostgresqlConnectionFactoryProvider.SCHEMA, dbSchema)
            .build()
    )

    @Bean
    fun transactionManager(connectionFactory: ConnectionFactory): ReactiveTransactionManager =
        R2dbcTransactionManager(connectionFactory)
}
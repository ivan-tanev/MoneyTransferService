package com.tanev.moneytransferservice.config

import io.r2dbc.spi.ConnectionFactory
import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.impl.DSL
import org.postgresql.ds.PGSimpleDataSource
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class JooqConfig(
    @Value("\${spring.r2dbc.username}") private val dbUser: String,
    @Value("\${spring.r2dbc.password}") private val dbPassword: String,
    @Value("\${spring.r2dbc.name}") private val dbName: String,
    @Value("\${spring.r2dbc.port}") private val dbPort: Int,
) {

    private val dataSource = PGSimpleDataSource().apply {
        user = dbUser
        password = dbPassword
        databaseName = dbName
        portNumbers = intArrayOf(dbPort)
    }

    @Bean
    fun dslContext(connectionFactory: ConnectionFactory): DSLContext =
        DSL.using(
            dataSource,
            SQLDialect.POSTGRES
        ).dsl()

    @Bean
    fun configuration(dslContext: DSLContext): Configuration = dslContext.configuration()
}
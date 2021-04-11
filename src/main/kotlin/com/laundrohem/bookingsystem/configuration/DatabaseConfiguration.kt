package com.laundrohem.bookingsystem.configuration

import io.r2dbc.postgresql.PostgresqlConnectionConfiguration
import io.r2dbc.postgresql.PostgresqlConnectionFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories

@Configuration
@EnableR2dbcRepositories
class DatabaseConfiguration : AbstractR2dbcConfiguration() {

    @Value("\${laundrohem.postgres.host}")
    lateinit var host: String

    @Value("\${laundrohem.postgres.database}")
    lateinit var database: String

    @Value("\${laundrohem.postgres.username}")
    lateinit var username: String

    @Value("\${laundrohem.postgres.password}")
    lateinit var password: String


    @Bean
    override fun connectionFactory(): PostgresqlConnectionFactory {
         return PostgresqlConnectionFactory(
             PostgresqlConnectionConfiguration.builder()
                 .host(host)
                 .database(database)
                 .username(username)
                 .password(password)
                 .build()
         )
    }
}
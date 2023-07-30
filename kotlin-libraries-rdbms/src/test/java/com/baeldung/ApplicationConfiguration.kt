package com.baeldung

import com.zaxxer.hikari.HikariDataSource
import org.jooq.SQLDialect
import org.jooq.impl.DataSourceConnectionProvider
import org.jooq.impl.DefaultConfiguration
import org.jooq.impl.DefaultDSLContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import javax.sql.DataSource

@Configuration
open class ApplicationConfiguration {

    @Bean
    open fun dataSource(): DataSource {
        val jdbcDataSource = HikariDataSource();
        jdbcDataSource.jdbcUrl = "jdbc:postgresql://localhost:5999/local"
        jdbcDataSource.username = "local"
        jdbcDataSource.password = "local"
        return jdbcDataSource
    }

    @Bean
    open fun jdbcTemplate() : JdbcTemplate {
        return JdbcTemplate(dataSource())
    }

    @Bean
    open fun dsl(): DefaultDSLContext {
        return DefaultDSLContext(configuration())
    }

    @Bean
    open fun transactionManager(): DataSourceTransactionManager {
        return DataSourceTransactionManager(dataSource())
    }

    @Bean
    open fun connectionProvider(): DataSourceConnectionProvider {
        return DataSourceConnectionProvider(dataSource())
    }

    @Bean
    open fun configuration(): DefaultConfiguration {
        val jooqConfiguration = DefaultConfiguration()
        jooqConfiguration.set(connectionProvider())
        jooqConfiguration.set(SQLDialect.POSTGRES)
        return jooqConfiguration
    }
}
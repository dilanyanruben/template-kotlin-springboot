package com.sfl.kotlin.persistence.configuration.datasource

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 3:52 PM
 */
@Configuration
class DataSourceConfiguration(@Value("\${persistence.database.username}") val databaseUserName: String,
                              @Value("\${persistence.database.password}") val databasePassword: String,
                              @Value("\${persistence.database.url}") val databaseConnectionUrl: String,
                              @Value("\${persistence.database.driverclassname}") val databaseDriverClassName: String,
                              @Value("\${persistence.pool.maxsize}") val connectionPoolMaximumSize: Int) {

    //region Companion objects
    companion object {
        private val LOGGER = LoggerFactory.getLogger(DataSourceConfiguration::class.java)!!
    }
    //endregion

    //region Configuration factory methods
    @Bean
    fun createDataSource(): DataSource {
        LOGGER.debug("Initializing database datasource")
        // Create the connection pool
        val hikariDataSource = HikariDataSource(createConfiguration())
        LOGGER.debug("Successfully created Hikari datasource - {}", hikariDataSource)
        return hikariDataSource
    }
    //endregion

    //region Private utility methods
    private fun createConfiguration(): HikariConfig {
        val hikariConfig = HikariConfig()
        hikariConfig.username = databaseUserName
        hikariConfig.password = databasePassword
        hikariConfig.driverClassName = databaseDriverClassName
        hikariConfig.jdbcUrl = databaseConnectionUrl
        hikariConfig.maximumPoolSize = connectionPoolMaximumSize
        LOGGER.debug("Successfully created Hikari datasource configuration - {}", hikariConfig)
        return hikariConfig
    }
    //endregion
}
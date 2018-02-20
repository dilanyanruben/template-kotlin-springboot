package com.sfl.kotlin.persistence.configuration.jpa

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import java.util.*


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 4:25 PM
 */
@Configuration
class JpaVendorAdapterConfiguration(@Value("\${persistence.hibernate.outputsqlstatements}") val outputSqlStatements: Boolean,
                                    @Value("\${persistence.hibernate.generateddl}") val generateDataDefinitionLanguage: Boolean,
                                    @Value("\${persistence.hibernate.databasedialect}") val databaseDialect: String,
                                    @Value("\${persistence.hibernate.formatsql}") val hibernateFormatSql: String,
                                    @Value("\${persistence.hibernate.hbmddlconversion}") val hibernateHbmToDllConversion: String,
                                    @Value("\${persistence.hibernate.generatestatistics}") val hibernateGenerateStatistics: Boolean,
                                    @Value("\${persistence.hibernate.jdbc.fetchsize}") val hibernateJdbcFetchSize: Int,
                                    @Value("\${persistence.hibernate.jdbc.batchsize}") val hibernateJdbcBatchSize: Int) {

    //region Companion object
    companion object {

        private val LOGGER = LoggerFactory.getLogger(JpaVendorAdapterConfiguration::class.java)!!

        //region Constants
        private const val HIBERNATE_FORMAT_SQL = "hibernate.format_sql"

        private const val HIBERNATE_SCHEMA_HBM_TO_DLL = "hibernate.hbm2ddl.auto"

        private const val HIBERNATE_GENERATE_STATISTICS = "hibernate.generate_statistics"

        private const val HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size"

        private const val HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size"
        //endregion
    }
    //endregion

    //region Configuration factory methods
    @Bean
    fun createJpaVendorAdapter(): JpaVendorAdapter {
        LOGGER.debug("Initializing Hibernate JPA vendor adapter")
        val hibernateJpaVendorAdapter = HibernateJpaVendorAdapter()
        hibernateJpaVendorAdapter.setDatabasePlatform(databaseDialect)
        hibernateJpaVendorAdapter.setGenerateDdl(generateDataDefinitionLanguage)
        hibernateJpaVendorAdapter.setShowSql(outputSqlStatements)
        LOGGER.debug("Successfully initialized Hibernate JPA vendor adapter - {}", hibernateJpaVendorAdapter)
        return hibernateJpaVendorAdapter
    }

    @Bean("jpaVendorProperties")
    fun createJpaVendorProperties(): Properties {
        LOGGER.debug("Initializing JPA vendor properties")
        val properties = Properties()
        properties.setProperty(HIBERNATE_FORMAT_SQL, hibernateFormatSql)
        properties.setProperty(HIBERNATE_SCHEMA_HBM_TO_DLL, hibernateHbmToDllConversion)
        properties.setProperty(HIBERNATE_GENERATE_STATISTICS, hibernateGenerateStatistics.toString())
        properties.setProperty(HIBERNATE_JDBC_FETCH_SIZE, hibernateJdbcFetchSize.toString())
        properties.setProperty(HIBERNATE_JDBC_BATCH_SIZE, hibernateJdbcBatchSize.toString())
        LOGGER.debug("Successfully initialized JPA vendor properties - {}", properties)
        return properties
    }
    //endregion
}
package com.sfl.kotlin.persistence.configuration.jpa

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import javax.persistence.EntityManagerFactory


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 4:21 PM
 */
@Configuration
class JpaTransactionManagerConfiguration(@Autowired val entityManagerFactory: EntityManagerFactory) {

    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(JpaTransactionManagerConfiguration::class.java)!!
    }
    //endregion

    //region Configuration factory methods
    @Bean
    fun createTransactionManager(): PlatformTransactionManager {
        LOGGER.debug("Creating transaction manager")
        val transactionManager = JpaTransactionManager()
        transactionManager.entityManagerFactory = entityManagerFactory
        LOGGER.debug("Successfully created transaction manager - {}", transactionManager)
        return transactionManager
    }
    //endregion
}
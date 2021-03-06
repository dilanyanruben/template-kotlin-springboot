package com.sfl.kotlin.persistence.configuration.jpa

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.orm.jpa.JpaVendorAdapter
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import java.util.*
import javax.persistence.EntityManagerFactory
import javax.sql.DataSource


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 4:05 PM
 */
@Configuration
class JpaEntityManagerFactoryConfiguration(@Autowired val dataSource: DataSource,
                                           @Autowired val jpaVendorAdapter: JpaVendorAdapter,
                                           @Qualifier("jpaVendorProperties") val jpaVendorProperties: Properties,
                                           @Value("\${persistence.jpa.persistenceunitname}") val persistenceUnitName: String) {

    //region Companion object
    companion object {

        private val LOGGER = LoggerFactory.getLogger(JpaEntityManagerFactoryConfiguration::class.java)!!

        //region Constants
        private const val JPA_PACKAGES_TO_SCAN = "com.sfl.kotlin.domain"
        //endregion
    }
    //endregion

    //region Configuration factory methods
    @Bean("entityManagerFactory")
    fun createEntityManagerFactory(): EntityManagerFactory {
        LOGGER.debug("Initializing entity manager factory")
        val entityManagerFactoryBean = LocalContainerEntityManagerFactoryBean()
        entityManagerFactoryBean.dataSource = dataSource
        entityManagerFactoryBean.persistenceUnitName = persistenceUnitName
        entityManagerFactoryBean.jpaVendorAdapter = jpaVendorAdapter
        entityManagerFactoryBean.setJpaProperties(jpaVendorProperties)
        entityManagerFactoryBean.setPackagesToScan(JPA_PACKAGES_TO_SCAN)
        entityManagerFactoryBean.afterPropertiesSet()
        LOGGER.debug("Successfully created entity manager factory - {}", entityManagerFactoryBean)
        return entityManagerFactoryBean.`object`!!
    }
    //endregion

}
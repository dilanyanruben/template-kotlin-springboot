package com.sfl.kotlin.persistence.configuration.jpa

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.data.jpa.repository.config.EnableJpaRepositories


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 4:18 PM
 */
@Configuration
@EnableJpaRepositories(basePackages = ["com.sfl.kotlin.persistence.repositories"])
class JpaRepositoriesConfiguration {

    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(JpaRepositoriesConfiguration::class.java)!!
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing JPA repositories configuration")
    }
    //endregion

}
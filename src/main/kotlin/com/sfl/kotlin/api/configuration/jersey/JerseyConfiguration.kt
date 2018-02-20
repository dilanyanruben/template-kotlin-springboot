package com.sfl.kotlin.api.configuration.jersey

import com.sfl.kotlin.api.resources.health.HealthCheckResource
import org.glassfish.jersey.server.ResourceConfig
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 2:48 PM
 */
@Configuration
class JerseyConfiguration : ResourceConfig() {

    //region Companion object
    companion object {
        val LOGGER = LoggerFactory.getLogger(JerseyConfiguration::class.java)!!
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing Jersey configuration")
        // Register resources
        register(HealthCheckResource::class.java)
    }
    //endregion
}
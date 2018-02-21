package com.sfl.kotlin.api.rest.configuration.jersey

import com.sfl.kotlin.api.rest.resources.health.HealthCheckResource
import com.sfl.kotlin.api.rest.resources.user.UserResource
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
        private val LOGGER = LoggerFactory.getLogger(JerseyConfiguration::class.java)!!
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing Jersey configuration")
        // Register resources
        register(HealthCheckResource::class.java)
        register(UserResource::class.java)
    }
    //endregion
}
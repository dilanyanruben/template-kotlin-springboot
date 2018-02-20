package com.sfl.kotlin.api.resources.health

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Response


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 2:58 PM
 */

@Path("health")
@Component
class HealthCheckResource {
    //region companion objects
    companion object {
        private val LOGGER = LoggerFactory.getLogger(HealthCheckResource::class.java)!!
    }
    //endregion

    //region REST endpoints methods
    @GET
    @Produces("text/plain")
    fun getHealthStatus(): Response {
        LOGGER.debug("Processing health check request")
        return Response.ok("ALIVE").build()
    }
    //endregion
}
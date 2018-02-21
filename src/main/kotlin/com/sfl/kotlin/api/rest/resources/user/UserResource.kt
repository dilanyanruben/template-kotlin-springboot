package com.sfl.kotlin.api.rest.resources.user

import com.sfl.kotlin.api.facade.user.UserResourceFacade
import com.sfl.kotlin.api.facade.user.model.UserModel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.ws.rs.*
import javax.ws.rs.core.Response


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 4:33 PM
 */
@Path("user")
@Component
@Produces("application/json")
class UserResource(@Autowired private val userResourceFacade: UserResourceFacade) {

    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserResource::class.java)
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing")
    }
    //endregion

    //region REST endpoints
    @GET
    @Path("/{id}")
    fun getUser(@PathParam("id") id: Long): Response {
        LOGGER.debug("Retrieving user for the provided id - {}", id)
        return Response.ok(userResourceFacade.getUser(id)).build()
    }

    @GET
    @Path("/all")
    fun getAllUsers(): Response {
        LOGGER.debug("Retrieving all users")
        return Response.ok(userResourceFacade.getAll()).build()
    }

    @POST
    @Consumes("application/json")
    fun createUser(user: UserModel): Response {
        LOGGER.debug("Creating new user for the provided model - {}", user)
        return Response.ok(userResourceFacade.createUser(user)).build()
    }
    //endregion
}
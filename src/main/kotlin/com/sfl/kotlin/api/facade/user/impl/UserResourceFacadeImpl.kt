package com.sfl.kotlin.api.facade.user.impl

import com.sfl.kotlin.api.facade.user.UserResourceFacade
import com.sfl.kotlin.api.facade.user.model.CreateUserModel
import com.sfl.kotlin.api.facade.user.model.ViewUserModel
import com.sfl.kotlin.domain.user.dto.CreateUserDto
import com.sfl.kotlin.domain.user.dto.UserDto
import com.sfl.kotlin.services.user.UserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 4:34 PM
 */
@Component
class UserResourceFacadeImpl(@Autowired private val userService: UserService) : UserResourceFacade {

    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserResourceFacadeImpl::class.java)!!
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing")
    }
    //endregion

    //region Public interface methods
    override fun getUser(id: Long): ViewUserModel {
        LOGGER.debug("Retrieving user for the provided id - {}", id)
        val result = userService.getById(id).let { ViewUserModel(it.id, it.firstName, it.lastName) }
        LOGGER.debug("Successfully retrieved user model - {} for id - {}", result, id)
        return result
    }

    override fun createUser(user: CreateUserModel): ViewUserModel {
        LOGGER.debug("Creating user for the provided model - {}", user)
        val result = user
                .let { CreateUserDto(UserDto(it.firstName, it.lastName)) }
                .let { userService.create(it) }
                .let { ViewUserModel(it.id, it.firstName, it.lastName) }
        LOGGER.debug("Successfully created user for the provided model - {}, result - {}", user, result)
        return result
    }

    override fun getAll(): List<ViewUserModel> {
        LOGGER.debug("Retrieving list all users")
        val result = userService.getAll().map { it.let { ViewUserModel(it.id, it.firstName, it.lastName) } }
        LOGGER.debug("Successfully retrieved list of all users - {}", result)
        return result
    }
    //endregion
}
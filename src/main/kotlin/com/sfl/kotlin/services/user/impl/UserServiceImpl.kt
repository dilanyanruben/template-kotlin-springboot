package com.sfl.kotlin.services.user.impl

import com.sfl.kotlin.domain.user.model.User
import com.sfl.kotlin.persistence.repositories.user.UserRepository
import com.sfl.kotlin.services.user.UserService
import com.sfl.kotlin.services.user.exception.UserNotFoundForIdException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:43 PM
 */
@Service
class UserServiceImpl(@Autowired val userRepository: UserRepository) : UserService {

    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(UserServiceImpl::class.java)!!
    }
    //endregion

    //region Initialization
    init {
        LOGGER.debug("Initializing")
    }
    //endregion

    //region Public interface methods
    override fun getById(id: Long): User {
        LOGGER.debug("Retrieving user for the provided id - {}", id)
        val user: User = userRepository.findById(id).orElseThrow({ UserNotFoundForIdException(id) })!!
        LOGGER.debug("Successfully retrieved user for the provided id - {}, user - {}", id, user)
        return user
    }
    //endregion
}
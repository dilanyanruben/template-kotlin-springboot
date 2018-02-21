package com.sfl.kotlin.services.user.impl

import com.sfl.kotlin.domain.user.dto.CreateUserDto
import com.sfl.kotlin.domain.user.model.User
import com.sfl.kotlin.persistence.repositories.user.UserRepository
import com.sfl.kotlin.services.user.UserService
import com.sfl.kotlin.services.user.exception.UserNotFoundForIdException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:43 PM
 */
@Service
class UserServiceImpl(@Autowired private val userRepository: UserRepository) : UserService {

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

    @Transactional
    override fun create(dto: CreateUserDto): User {
        LOGGER.debug("Creating new user for the provided DTO - {}", dto)
        var user = User(dto.userDto.firstName, dto.userDto.lastName)
        user = userRepository.save(user)
        LOGGER.debug("Successfully created user for the provided DTO - {}, user - {}", dto, user)
        return user
    }

    override fun getAll(): List<User> {
        LOGGER.debug("Retrieving all users")
        val users = userRepository.findAll()
        LOGGER.debug("Successfully retrieved users - {}", users)
        return users
    }
    //endregion
}
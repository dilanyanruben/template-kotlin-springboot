package com.sfl.kotlin.services.user

import com.sfl.kotlin.domain.user.dto.CreateUserDto
import com.sfl.kotlin.domain.user.model.User


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:42 PM
 */
interface UserService {

    /**
     * Retrieves user for the provided id
     *
     * @param id
     * @return user
     *
     */
    fun getById(id: Long): User

    /**
     * Creates user for the provided DTO
     *
     * @param dto
     * @return user
     *
     */
    fun create(dto: CreateUserDto): User

    /**
     * Retrieves list of all users
     *
     * @return users
     *
     */
    fun getAll(): List<User>
}
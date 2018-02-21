package com.sfl.kotlin.api.facade.user

import com.sfl.kotlin.api.facade.user.model.UserModel


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 4:33 PM
 */
interface UserResourceFacade {

    /**
     *
     * Retrieves user for the provided id
     *
     * @param id
     * @return user
     *
     */
    fun getUser(id: Long): UserModel

    /**
     * Creates user for the provided model
     *
     * @param user
     * @return createdUser
     *
     */
    fun createUser(user: UserModel): UserModel


    /**
     * Retrieves all users
     *
     * @return users
     *
     */
    fun getAll(): List<UserModel>
}
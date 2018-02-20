package com.sfl.kotlin.services.user

import com.sfl.kotlin.domain.user.model.User


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:42 PM
 */
interface UserService {

    fun getById(id: Long): User
}
package com.sfl.kotlin.services.user.exception


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:48 PM
 */
class UserNotFoundForIdException(val id: Long) : RuntimeException("User not found for the provided id - $id") {
}
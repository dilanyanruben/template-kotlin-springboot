package com.sfl.kotlin.persistence.repositories.user

import com.sfl.kotlin.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:41 PM
 */
@Repository
interface UserRepository : JpaRepository<User, Long> {
}
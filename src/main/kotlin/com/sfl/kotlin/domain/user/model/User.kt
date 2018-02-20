package com.sfl.kotlin.domain.user.model

import com.sfl.kotlin.domain.common.AbstractDomainEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 6:20 PM
 */

@Entity
@Table(name = "user")
data class User(@Column(name = "first_name", nullable = false) var firstName: String,
                @Column(name = "last_name", nullable = false) var lastName: String) : AbstractDomainEntity() {
}
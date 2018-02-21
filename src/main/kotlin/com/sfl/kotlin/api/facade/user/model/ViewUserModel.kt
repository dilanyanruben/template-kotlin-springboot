package com.sfl.kotlin.api.facade.user.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/21/18
 * Time: 4:36 PM
 */
data class ViewUserModel(@JsonProperty("id") val id: Long,
                         @JsonProperty("firstName") val firstName: String,
                         @JsonProperty("lastName") val lastName: String)
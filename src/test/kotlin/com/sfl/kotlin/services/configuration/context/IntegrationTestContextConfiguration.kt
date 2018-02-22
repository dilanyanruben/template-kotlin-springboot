package com.sfl.kotlin.services.configuration.context

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.test.context.ActiveProfiles


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/22/18
 * Time: 2:39 PM
 */

@Configuration
@ComponentScan(value = ["com.sfl.kotlin.services", "com.sfl.kotlin.persistence"])
@ActiveProfiles("integrationtest")
class IntegrationTestContextConfiguration
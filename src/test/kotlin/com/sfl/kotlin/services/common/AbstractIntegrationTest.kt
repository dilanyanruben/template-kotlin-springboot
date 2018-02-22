package com.sfl.kotlin.services.common

import com.sfl.kotlin.services.configuration.context.IntegrationTestContextConfiguration
import org.springframework.boot.test.context.ConfigFileApplicationContextInitializer
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig
import javax.transaction.Transactional


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/22/18
 * Time: 2:25 PM
 */
@SpringJUnitConfig(IntegrationTestContextConfiguration::class, initializers = [(ConfigFileApplicationContextInitializer::class)])
@Transactional
abstract class AbstractIntegrationTest
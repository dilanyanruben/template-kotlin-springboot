package com.sfl.kotlin.api.configuration.application

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 11:45 AM
 */
@Configuration
@ComponentScan(value = ["com.sfl.kotlin"])
class ApplicationRestRunner {

    //region Companion object
    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(ApplicationRestRunner::class.java)!!
    }
    //endregion
}

fun main(args: Array<String>) {
    ApplicationRestRunner.LOGGER.debug("Starting Spring boot REST application")
    SpringApplication.run(ApplicationRestRunner::class.java)
    ApplicationRestRunner.LOGGER.debug("Successfully started Spring boot REST application")
}
package com.sfl.kotlin.api.configuration.container

import org.slf4j.LoggerFactory
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 11:58 AM
 */
@Configuration
class ServletContainerConfiguration {
    //region Companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(ServletContainerConfiguration::class.java)!!
    }
    //endregion

    //region Configuration methods
    @Bean
    fun createServletConfiguration(): ConfigurableServletWebServerFactory {
        LOGGER.debug("Creating servlet container configuration")
        val jettyServletWebServerFactory = JettyServletWebServerFactory()
        jettyServletWebServerFactory.port = 8080
        LOGGER.debug("Successfully created servlet container configuration - {}", jettyServletWebServerFactory)
        return jettyServletWebServerFactory;
    }
    //endregion
}
package com.sfl.kotlin.api.rest.configuration.servlet

import com.sfl.kotlin.api.rest.configuration.jersey.JerseyConfiguration
import org.glassfish.jersey.servlet.ServletContainer
import org.slf4j.LoggerFactory
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


/**
 * User: Ruben Dilanyan
 * Company: SFL LLC
 * Date: 2/20/18
 * Time: 2:52 PM
 */
@Configuration
class JerseyServletConfiguration {
    //region companion object
    companion object {
        private val LOGGER = LoggerFactory.getLogger(JerseyServletConfiguration::class.java)!!
    }
    //endregion

    //region Configuration methods
    @Bean
    fun createJerseyServletConfiguration(): ServletRegistrationBean<ServletContainer> {
        LOGGER.debug("Initializing Jersey servlet")
        val servletRegistrationBean = ServletRegistrationBean<ServletContainer>()
        servletRegistrationBean.setServlet(ServletContainer())
        servletRegistrationBean.setName("servlet.jersey")
        servletRegistrationBean.urlMappings.add("/*")
        servletRegistrationBean.order = 0
        servletRegistrationBean.initParameters["javax.ws.rs.Application"] = JerseyConfiguration::class.java.name
        LOGGER.debug("Successfully initialized Jersey servlet - {}", servletRegistrationBean)
        return servletRegistrationBean
    }
    //endregion
}
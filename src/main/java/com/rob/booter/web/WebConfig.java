package com.rob.booter.web;

import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Rob Benton
 */
@Configuration
@EnableWebMvc
public class WebConfig
{
    @Bean
    public ServletWebServerFactory jettyServerFactory()
    {
        return new JettyServletWebServerFactory();
    }
}

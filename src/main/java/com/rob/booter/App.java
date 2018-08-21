package com.rob.booter;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Rob Benton
 */
@ComponentScan
@Configuration
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }
}

package com.rob.booter;

import com.rob.booter.meta.ReleaseVersion;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author Rob Benton
 */
@SpringBootApplication
public class App
{
    public static void main(String[] args)
    {
        SpringApplication.run(App.class, args);
    }

    /**
     * The release bean which returns version info.
     *
     * @return {@link ReleaseVersion}
     */
    @Bean
    public ReleaseVersion release()
    {
        return () -> getClass().getPackage().getImplementationVersion();
    }
}

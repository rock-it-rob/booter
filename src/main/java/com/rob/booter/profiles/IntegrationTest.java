package com.rob.booter.profiles;

import com.rob.booter.meta.ReleaseVersion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * IntegrationTest provides spring configuration for the integration testing
 * profile.
 *
 * @author Rob Benton
 */
@Configuration
@Profile(IntegrationTest.PROFILE)
@PropertySource(IntegrationTest.POM_PROPERTIES)
public class IntegrationTest
{
    public static final String PROFILE = "integration-test";
    static final String POM_PROPERTIES = "classpath:pom.properties";

    @Bean
    public ReleaseVersion releaseVersion(@Value("${version}") String version)
    {
        return () -> version;
    }
}

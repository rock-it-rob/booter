package com.rob.booter.profiles;

import com.rob.booter.meta.ReleaseVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Default provides spring beans and configuration that are only enabled when
 * running in the default profile.
 *
 * @author Rob Benton
 */
@Configuration
@Profile(Default.PROFILE)
public class Default
{
    public static final String PROFILE = "default";

    @Bean
    public ReleaseVersion releaseVersion()
    {
        return () -> getClass().getPackage().getImplementationVersion();
    }
}

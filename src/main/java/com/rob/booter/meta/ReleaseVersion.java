package com.rob.booter.meta;

/**
 * ReleaseVersion provides meta-information about the release information of this
 * application.
 *
 * @author Rob Benton
 */
@FunctionalInterface
public interface ReleaseVersion
{
    /**
     * Retrieves the version number of the application.
     *
     * @return version number as a <em>String</em>.
     */
    String version();
}

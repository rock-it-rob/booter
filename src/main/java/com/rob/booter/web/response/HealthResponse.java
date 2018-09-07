package com.rob.booter.web.response;

/**
 * HealthResponse is a reponse POJO for the health of the applicaiton.
 *
 * @author Rob Benton
 */
public class HealthResponse
{
    private boolean healthy;
    private String version;

    public HealthResponse() {}

    /**
     * Constructor for creating response based on all fields.
     *
     * @param healthy whether or not the application is healthy.
     * @param version version number of the application.
     */
    public HealthResponse(boolean healthy, String version)
    {
        this.healthy = healthy;
        this.version = version;
    }

    public boolean isHealthy()
    {
        return healthy;
    }

    public String getVersion()
    {
        return version;
    }
}

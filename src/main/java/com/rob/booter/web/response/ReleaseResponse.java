package com.rob.booter.web.response;

/**
 * ReleaseResponse is a POJO response class for (de)serialization.
 *
 * @author Rob Benton
 */
public class ReleaseResponse
{
    private String version;

    public ReleaseResponse() {}

    public ReleaseResponse(String version)
    {
        this.version = version;
    }

    public void setVersion(String version) { this.version = version; }

    public String getVersion() { return version; }
}

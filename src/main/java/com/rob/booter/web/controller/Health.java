package com.rob.booter.web.controller;

import com.rob.booter.meta.ReleaseVersion;
import com.rob.booter.web.response.HealthResponse;
import org.eclipse.jetty.server.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Health is the controller providing methods that report on the health of the
 * application.
 *
 * @author Rob Benton
 */
@RestController
@RequestMapping(Health.URI)
public class Health
{
    public static final String URI = "/health";

    @Autowired
    private ReleaseVersion releaseVersion;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthResponse> get()
    {
        return ResponseEntity.status(Response.SC_OK).body(
            new HealthResponse(true, releaseVersion.version())
        );
    }
}

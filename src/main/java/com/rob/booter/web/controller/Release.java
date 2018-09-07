package com.rob.booter.web.controller;

import com.rob.booter.meta.ReleaseVersion;
import com.rob.booter.web.response.ReleaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Release is a controller providing restful methods for retrieving information
 * on this release.
 *
 * @author Rob Benton
 */
@RestController
@RequestMapping(Release.URI)
public class Release
{
    public final static String URI = "/release";

    @Autowired
    private ReleaseVersion releaseVersion;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ReleaseResponse> version()
    {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ReleaseResponse(releaseVersion.version()));
    }
}

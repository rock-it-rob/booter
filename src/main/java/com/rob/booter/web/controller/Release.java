package com.rob.booter.web.controller;

import com.rob.booter.meta.ReleaseVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Release is a controller providing restful methods for retrieving information
 * on this release.
 *
 * @author Rob Benton
 */
@RestController
@RequestMapping("/release")
public class Release
{
    @Autowired
    private ReleaseVersion releaseVersion;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> version()
    {
        HashMap<String, String> result = new HashMap<>();
        result.put("version", releaseVersion.version());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}

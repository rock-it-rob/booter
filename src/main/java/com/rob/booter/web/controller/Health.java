package com.rob.booter.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.server.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author Rob Benton
 */
@RestController
@RequestMapping("/health")
public class Health
{
    private final ObjectMapper objectMapper;

    public Health()
    {
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> get() throws JsonProcessingException
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", "healthy");
        final String result = objectMapper.writeValueAsString(map);

        return ResponseEntity.status(Response.SC_OK).body(result);
    }
}

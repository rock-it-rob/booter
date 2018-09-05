package com.rob.booter.test;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.booter.web.response.AbstractErrorResponse;
import com.rob.booter.web.response.JsonErrorResponse;
import com.rob.booter.web.response.entity.JsonErrorEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * JsonProcessingExceptionAdvisorIT is an integration test class that tests the
 * functionality of the {@link com.rob.booter.web.advisor.JsonProcessingExceptionAdvisor}
 * class.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JsonProcessingExceptionAdvisorIT
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Test configuration class that will load the necessary beans and configuration.
     */
    @TestConfiguration
    @Import(DummyRestController.class)
    static class TestConfig
    {
    }

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testPost() throws IOException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        final String badRequest = "{ \"test\": \"test\", }";
        HttpEntity<String> entity = new HttpEntity<>(badRequest, headers);
        ResponseEntity<JsonErrorResponse> response = restTemplate.postForEntity(
            DummyRestController.URI, entity, JsonErrorResponse.class
        );
        /*
        ResponseEntity<String> response = restTemplate.postForEntity(
            DummyRestController.URI, entity, String.class
        );
        */
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        JsonErrorResponse errorResponse = response.getBody();
        assertNotNull(errorResponse);
        final String text = objectMapper.writeValueAsString(errorResponse);
        System.out.println(text);
    }

    /**
     * DummyRestController supplies a spring rest controller for testing purposes.
     */
    @RestController
    @RequestMapping(DummyRestController.URI)
    private static final class DummyRestController
    {
        static final String URI = "/dummy";

        @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
        public void post(@RequestBody Map<String, Object> request, @RequestHeader HttpHeaders headers)
        {
        }
    }
}

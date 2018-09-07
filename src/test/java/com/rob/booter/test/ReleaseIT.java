package com.rob.booter.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.booter.profiles.IntegrationTest;
import com.rob.booter.web.controller.Release;
import com.rob.booter.web.response.ReleaseResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * ReleaseIT is an integration test suite for the {@link com.rob.booter.web.controller.Release}
 * restful resource.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles(IntegrationTest.PROFILE)
public class ReleaseIT
{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger log = LoggerFactory.getLogger(ReleaseIT.class);

    @Autowired
    private TestRestTemplate restTemplate;

    /**
     * Tests that the version resource is reachable, returns a 200 status, and
     * contains a version number that is correct.
     *
     * @throws JsonProcessingException if there is a problem parsing the response. This is
     *                                 not expected.
     */
    @Test
    public void testVersion() throws JsonProcessingException
    {
        ResponseEntity<ReleaseResponse> response = restTemplate.getForEntity(
            Release.URI, ReleaseResponse.class
        );
        assertNotNull(response);
        ReleaseResponse body = response.getBody();

        try
        {
            assertEquals(HttpStatus.OK, response.getStatusCode());
            assertNotNull(body);
            final String version = body.getVersion();
            assertNotNull(version);
        }
        catch (AssertionError e)
        {
            log.error("Response: " + objectMapper.writeValueAsString(body));
            throw e;
        }

    }
}

package com.rob.booter.test;

import com.rob.booter.web.controller.Health;
import com.rob.booter.web.response.HealthResponse;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * HealthIT is an integration test suite for the {@link com.rob.booter.web.controller.Health}
 * restful resource.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HealthIT
{
    @Autowired
    private TestRestTemplate restTemplate;

    private static String versionTest;

    @BeforeClass
    public static void beforeClass()
    {
        versionTest = HealthIT.class.getPackage().getImplementationVersion();
    }

    /**
     * Test that the {@link Health} resource returns a {@link HealthResponse} and
     * that the status is healthy and the version is correct.
     */
    @Test
    public void testHealth()
    {
        ResponseEntity<HealthResponse> response = restTemplate.getForEntity(
            Health.URI, HealthResponse.class
        );
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        HealthResponse body = response.getBody();
        assertNotNull(body);
        assertTrue(body.isHealthy());
        assertEquals(versionTest, body.getVersion());
    }
}

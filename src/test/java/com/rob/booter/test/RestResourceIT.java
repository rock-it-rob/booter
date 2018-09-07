package com.rob.booter.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.booter.profiles.IntegrationTest;
import com.rob.booter.web.controller.Health;
import com.rob.booter.web.response.HealthResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

/**
 * RestResourceIT is an integration test suite for the spring MVC rest controllers.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(IntegrationTest.PROFILE)
@AutoConfigureMockMvc
public class RestResourceIT
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    //@Autowired
    //private TestRestTemplate restTemplate;

    /**
     * Tests the {@link Health} rest controller.
     * @throws Exception for any errors.
     */
    @Test
    public void testHealthController() throws Exception
    {
        RequestBuilder requestBuilder =
            MockMvcRequestBuilders.get(Health.URI);
        mockMvc.perform(requestBuilder).andDo(mvcResult -> {
            MockHttpServletResponse response = mvcResult.getResponse();
            // Verify the status is a 200.
            assertEquals(HttpStatus.OK.value(), response.getStatus());
            // Obtain the response as a JSON POJO.
            final String body = response.getContentAsString();
            HealthResponse entity = objectMapper.readValue(body, HealthResponse.class);
            assertNotNull(entity);
            // Verify that healthy is true.
            assertTrue(entity.isHealthy());
            // Verify version number is not null.
            assertNotNull(entity.getVersion());
        });
    }

    /**
     * Test that the {@link Health} resource returns a {@link HealthResponse} and
     * that the status is healthy and the version is correct.
     */
    /*
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
    */
}

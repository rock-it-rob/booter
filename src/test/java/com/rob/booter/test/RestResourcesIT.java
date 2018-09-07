package com.rob.booter.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.booter.profiles.IntegrationTest;
import com.rob.booter.web.controller.Health;
import com.rob.booter.web.controller.Release;
import com.rob.booter.web.response.HealthResponse;
import com.rob.booter.web.response.ReleaseResponse;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

/**
 * RestResourcesIT is an integration test suite for the spring MVC rest controllers.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(IntegrationTest.PROFILE)
@AutoConfigureMockMvc
public class RestResourcesIT
{
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the {@link Health} rest controller.
     *
     * @throws Exception for any errors.
     */
    @Test
    public void testHealthController() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get(Health.URI)).andDo(mvcResult -> {
            MockHttpServletResponse response = mvcResult.getResponse();
            // Verify the status is a 200.
            assertEquals(HttpStatus.OK.value(), response.getStatus());
            // Obtain the response as a JSON POJO.
            final String body = response.getContentAsString();
            HealthResponse healthResponse = objectMapper.readValue(body, HealthResponse.class);
            assertNotNull(healthResponse);
            // Verify that healthy is true.
            assertTrue(healthResponse.isHealthy());
            // Verify version number is not null.
            assertNotNull(healthResponse.getVersion());
        });
    }

    /**
     * Tests the {@link com.rob.booter.web.controller.Release} rest controller.
     *
     * @throws Exception for any errors.
     */
    @Test
    public void testReleaseController() throws Exception
    {
        mockMvc.perform(MockMvcRequestBuilders.get(Release.URI)).andDo(mvcResult -> {
            MockHttpServletResponse response = mvcResult.getResponse();
            // Verify the status is 200.
            assertEquals(HttpStatus.OK.value(), response.getStatus());
            // Obtain the response as a json POJO.
            final String body = response.getContentAsString();
            ReleaseResponse releaseResponse = objectMapper.readValue(body, ReleaseResponse.class);
            assertNotNull(releaseResponse);
            // Verify version is not empty.
            assertNotNull(releaseResponse.getVersion());
            assertNotEquals(0, releaseResponse.getVersion().length());
        });
    }
}

package com.rob.booter.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rob.booter.web.response.JsonErrorResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.junit.Assert.*;

/**
 * ExceptionAdvisorsIT is an integration test suite for testing the spring
 * MVC exception advisors.
 *
 * @author Rob Benton
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ExceptionAdvisorsIT
{
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String INVALID_JSON = "{ \"test\": \"bad\", }";


    @TestConfiguration
    @Import(DummyRestController.class)
    static class Config
    {
    }


    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the {@link com.rob.booter.web.advisor.JsonProcessingExceptionAdvisor} by
     * sending an invalid json object into a dummy rest controller.
     *
     * @throws Exception for any errors.
     */
    @Test
    public void testJsonProcessingExceptionAdvisor() throws Exception
    {
        mockMvc.perform(
            MockMvcRequestBuilders.post(DummyRestController.URI)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(INVALID_JSON)
        )
            .andDo(mvcResult -> {
                MockHttpServletResponse response = mvcResult.getResponse();
                // The status should be a 400.
                assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
                // The response content should be a JsonErrorResponse.
                final String body = response.getContentAsString();
                JsonErrorResponse jsonErrorResponse = objectMapper.readValue(body, JsonErrorResponse.class);
                assertNotNull(jsonErrorResponse);
                // Verify the message attribute contains a value.
                assertNotNull(jsonErrorResponse.getError().getMessage());
            });
    }

    /**
     * DummyRestController supplies a spring rest controller for testing purposes.
     */
    @RestController
    @RequestMapping(DummyRestController.URI)
    private static final class DummyRestController
    {
        static final String URI = "/dummy";

        @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
        public void post(@RequestBody Map<String, Object> body) {}
    }
}

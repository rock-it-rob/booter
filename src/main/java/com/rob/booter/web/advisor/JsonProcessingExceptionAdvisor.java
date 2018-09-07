package com.rob.booter.web.advisor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rob.booter.web.response.AbstractErrorResponse;
import com.rob.booter.web.response.JsonErrorResponse;
import com.rob.booter.web.response.entity.JsonErrorEntity;
import org.eclipse.jetty.server.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * JsonProcessingExceptionAdvisor provides an exception handler for spring mvc methods
 * that throw a {@link JsonProcessingException}.
 *
 * @author Rob Benton
 */
@ControllerAdvice
public class JsonProcessingExceptionAdvisor
{
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<AbstractErrorResponse> jsonProcessingException(JsonProcessingException e)
    {
        return ResponseEntity
            .status(Response.SC_BAD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON)
            .body(new JsonErrorResponse(new JsonErrorEntity(e)));
    }
}

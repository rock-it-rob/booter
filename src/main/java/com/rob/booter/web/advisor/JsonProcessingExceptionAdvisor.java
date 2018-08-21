package com.rob.booter.web.advisor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.rob.booter.web.response.ErrorResponse;
import com.rob.booter.web.response.entity.JsonError;
import org.eclipse.jetty.server.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Rob Benton
 */
@ControllerAdvice
public class JsonProcessingExceptionAdvisor
{
    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorResponse> jsonProcessingException(JsonProcessingException e)
    {
        return ResponseEntity
            .status(Response.SC_INTERNAL_SERVER_ERROR)
            .body(new ErrorResponse().addError(new JsonError(e)));
    }
}

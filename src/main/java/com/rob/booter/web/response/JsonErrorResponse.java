package com.rob.booter.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rob.booter.web.response.entity.JsonErrorEntity;

public class JsonErrorResponse extends AbstractErrorResponse<JsonErrorEntity>
{
    private static final String ERROR_NAME = "jsonProcessingError";

    /**
     * Default constructor.
     */
    public JsonErrorResponse() {}

    /**
     * Constructor containing the error.
     *
     * @param error T
     */
    public JsonErrorResponse(JsonErrorEntity error)
    {
        super(error);
    }

    @Override
    @JsonProperty(ERROR_NAME)
    public JsonErrorEntity getError() { return error; }
}

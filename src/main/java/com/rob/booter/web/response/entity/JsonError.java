package com.rob.booter.web.response.entity;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author Rob Benton
 */
public class JsonError extends AbstractErrorEntity
{
    private static final String TYPE = "jsonError";

    private final int line;
    private final int column;
    private final String message;

    public JsonError(JsonProcessingException e)
    {
        super(TYPE);
        final JsonLocation jsonLocation = e.getLocation();
        line = jsonLocation.getLineNr();
        column = jsonLocation.getColumnNr();
        message = e.getOriginalMessage();
    }

    public int getLine()
    {
        return line;
    }

    public int getColumn()
    {
        return column;
    }

    public String getMessage()
    {
        return message;
    }
}

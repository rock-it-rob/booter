package com.rob.booter.web.response.entity;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * JsonErrorEntity defines the properties of an error that occurred while parsing JSON.
 *
 * @author Rob Benton
 */
public class JsonErrorEntity
{
    private int line;
    private int column;
    private String message;

    public JsonErrorEntity() {}

    public JsonErrorEntity(JsonProcessingException e)
    {
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

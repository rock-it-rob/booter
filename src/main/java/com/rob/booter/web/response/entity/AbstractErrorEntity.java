package com.rob.booter.web.response.entity;

/**
 * @author Rob Benton
 */
public abstract class AbstractErrorEntity
{
    protected final String type;

    AbstractErrorEntity(String type)
    {
        this.type = type;
    }

    public String getType() { return type; }
}

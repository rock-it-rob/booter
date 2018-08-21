package com.rob.booter.web.response;

import com.rob.booter.web.response.entity.AbstractErrorEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rob Benton
 */
public class ErrorResponse
{
    private ArrayList<AbstractErrorEntity> errors;

    public ErrorResponse()
    {
        errors = new ArrayList<>();
    }

    public ErrorResponse addError(AbstractErrorEntity e)
    {
        errors.add(e);
        return this;
    }

    public List<AbstractErrorEntity> getErrors() { return errors; }
}

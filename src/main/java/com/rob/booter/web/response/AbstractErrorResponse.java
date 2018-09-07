package com.rob.booter.web.response;

/**
 * AbstractErrorResponse is the base error response POJO for JSON (de)serialization.
 * Exception handlers should return a sub-class.
 *
 * @author Rob Benton
 */
public abstract class AbstractErrorResponse<T>
{
    protected T error;

    protected AbstractErrorResponse() {}

    /**
     * Constructor containing the error.
     *
     * @param error T
     */
    protected AbstractErrorResponse(T error)
    {
        this.error = error;
    }

    public void setError(T error) { this.error = error; }

    /**
     * Gets the error object.
     * <p>
     * This method may be overriden in extending subclasses and, by using
     * the {@link com.fasterxml.jackson.annotation.JsonProperty} annotation,
     * define a more specific name for the error property.
     *
     * @return T error
     */
    public T getError() { return error; }
}

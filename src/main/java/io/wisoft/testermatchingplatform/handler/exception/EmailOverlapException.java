package io.wisoft.testermatchingplatform.handler.exception;

public class EmailOverlapException extends RuntimeException {

    public EmailOverlapException(final String message) {
        super(message);
    }
}

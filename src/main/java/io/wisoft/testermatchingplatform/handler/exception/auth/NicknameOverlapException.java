package io.wisoft.testermatchingplatform.handler.exception.auth;

public class NicknameOverlapException extends RuntimeException{
    public NicknameOverlapException(final String message) {
        super(message);
    }
}

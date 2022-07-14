package io.wisoft.testermatchingplatform.handler;

import io.wisoft.testermatchingplatform.handler.exception.auth.EmailNotEqualException;
import io.wisoft.testermatchingplatform.handler.exception.auth.EmailOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.auth.NicknameOverlapException;
import io.wisoft.testermatchingplatform.handler.exception.auth.PasswordNotEqualException;
import io.wisoft.testermatchingplatform.handler.exception.category.CategoryNotFoundException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailOverlapException.class)
    public ResponseEntity<ErrorResponse> emailOverlap(final EmailOverlapException e) {
        ErrorResponse errorResponse = generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(NicknameOverlapException.class)
    public ResponseEntity<ErrorResponse> nicknameOverlap(final NicknameOverlapException e) {
        ErrorResponse errorResponse = generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> categoryNotFound(final CategoryNotFoundException e) {
        ErrorResponse errorResponse = generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailNotEqualException.class)
    public ResponseEntity<ErrorResponse> emailNotEqual(final EmailNotEqualException e) {
        ErrorResponse errorResponse = generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ExceptionHandler(PasswordNotEqualException.class)
    public ResponseEntity<ErrorResponse> passwordNotEqual(final PasswordNotEqualException e) {
        ErrorResponse errorResponse = generateErrorResponseWithMessage(e.getMessage());

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }


    private ErrorResponse generateErrorResponseWithMessage(String errorMessage) {
        final ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.add(errorMessage);
        return errorResponse;
    }

}

@Getter
class ErrorResponse {
    final List<String> message;

    public ErrorResponse() {
        this(new ArrayList<>());
//        this.message = new ArrayList<>();
    }
    public ErrorResponse(List<String> message) {
        this.message = message;
    }

    void add(String message) {
        this.message.add(message);
    }
}

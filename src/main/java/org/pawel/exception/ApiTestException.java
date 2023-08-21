package org.pawel.exception;

public class ApiTestException extends RuntimeException {

    public ApiTestException(String message) {
        super(message);
    }

    public ApiTestException(String message, Throwable cause) {
        super(message, cause);
    }

}

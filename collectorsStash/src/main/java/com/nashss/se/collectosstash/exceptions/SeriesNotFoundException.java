package com.nashss.se.collectosstash.exceptions;

public class SeriesNotFoundException extends RuntimeException{

    private static final long serialVersionUID = -1353562416684063579L;

    /**
     * Exception with no message or cause.
     */
    public SeriesNotFoundException() {
        super();
    }
    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public SeriesNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public SeriesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public SeriesNotFoundException(Throwable cause) {
        super(cause);
    }
}

package com.nashss.se.collectorsstash.exceptions;

public class ComicBookNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -5311082217756617835L;

    /**
     * Exception with no message or cause.
     */
    public ComicBookNotFoundException() {
        super();
    }
    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public ComicBookNotFoundException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public ComicBookNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Exception with no message, but with a cause.
     * @param cause The original throwable resulting in this exception.
     */
    public ComicBookNotFoundException(Throwable cause) {
        super(cause);
    }

}

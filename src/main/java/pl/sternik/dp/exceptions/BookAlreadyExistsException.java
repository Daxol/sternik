package pl.sternik.dp.exceptions;

public class BookAlreadyExistsException extends Exception {
    private static final long serialVersionUID = -4576295597218170159L;

    public BookAlreadyExistsException() {
    }

    public BookAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
                                      boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public BookAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookAlreadyExistsException(String message) {
        super(message);
    }

    public BookAlreadyExistsException(Throwable cause) {
        super(cause);
    }
    
}

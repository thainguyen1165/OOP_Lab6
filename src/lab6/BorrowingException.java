package lab6;

public class BorrowingException extends RuntimeException {
    public BorrowingException(String message) {
        super(message);
    }

    public BorrowingException(String message, Throwable cause) {
        super(message, cause);
    }
}

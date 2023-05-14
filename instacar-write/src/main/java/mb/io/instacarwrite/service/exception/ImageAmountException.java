package mb.io.instacarwrite.service.exception;

public class ImageAmountException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ImageAmountException(String message) {
        super(message);
    }


    public ImageAmountException(String message, Throwable cause) {
        super(message,cause);
    }

}
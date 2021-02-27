package exception;

public class ApiException extends RuntimeException {

    public ApiException(String errorMessage) {
        super(errorMessage);
    }
}
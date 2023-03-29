package study.project.domain.exception;

public class WrongApproachException extends RuntimeException{
    public WrongApproachException() {
    }

    public WrongApproachException(String message) {
        super(message);
    }

    public WrongApproachException(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongApproachException(Throwable cause) {
        super(cause);
    }
}

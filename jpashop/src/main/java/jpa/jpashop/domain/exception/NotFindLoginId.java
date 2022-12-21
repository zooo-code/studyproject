package jpa.jpashop.domain.exception;

public class NotFindLoginId extends RuntimeException{
    public NotFindLoginId() {
    }

    public NotFindLoginId(String message) {
        super(message);
    }

    public NotFindLoginId(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindLoginId(Throwable cause) {
        super(cause);
    }
}

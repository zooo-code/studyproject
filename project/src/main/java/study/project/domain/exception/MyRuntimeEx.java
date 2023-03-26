package study.project.domain.exception;

public class MyRuntimeEx extends RuntimeException {
    public MyRuntimeEx() {
    }

    public MyRuntimeEx(String message) {
        super(message);
    }

    public MyRuntimeEx(String message, Throwable cause) {
        super(message, cause);
    }

    public MyRuntimeEx(Throwable cause) {
        super(cause);
    }
}

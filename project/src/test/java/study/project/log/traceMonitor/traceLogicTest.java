package study.project.log.traceMonitor;

import org.junit.jupiter.api.Test;
import study.project.log.logtrace.TraceStatus;

import static org.junit.jupiter.api.Assertions.*;

class traceLogicTest {

    @Test
    void begin_end() {
        TraceLogic trace = new TraceLogic();
        TraceStatus status = trace.begin("hello");
        trace.end(status);
    }
    @Test
    void begin_exception() {
        TraceLogic trace = new TraceLogic();
        TraceStatus status = trace.begin("hello");
        trace.exception(status, new IllegalStateException());
    }

    @Test
    void begin_end_level2() {
        TraceLogic trace = new TraceLogic();
        TraceStatus status1 = trace.begin("hello1");
        TraceStatus status2 = trace.beginSync(status1.getTraceId(), "hello2");
        trace.end(status2);
        trace.end(status1);
    }
}
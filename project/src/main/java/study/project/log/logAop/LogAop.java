package study.project.log.logAop;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import study.project.log.logtrace.LogTrace;
import study.project.log.logtrace.ThreadLocalLogTrace;
import study.project.log.logtrace.TraceStatus;

@Slf4j
@Aspect
@RequiredArgsConstructor
public class LogAop {

    private final LogTrace logTrace;

    @Around("execution(* study.project..*(..))")
    public Object logTraceTime(ProceedingJoinPoint joinPoint){
        TraceStatus status = null;

        try {
            String message = joinPoint.getSignature().toShortString();
            status = logTrace.begin(message);
            //로직 호출
            Object result = joinPoint.proceed();

            logTrace.end(status);
            return result;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}

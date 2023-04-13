package study.project.log.logtrace;


import lombok.extern.slf4j.Slf4j;
import study.project.log.TraceId;

/**
 * FieldLogTrace 는 TraceLogic 과 같은 기능을 한다.
 * TraceId 를 동기화 하는 부분만 파라미터를 사용하는 것에서 TraceId traceIdHolder 필드를 사용하도록 변경되었다.
 * 이제 직전 로그의 TraceId 는 파라미터로 전달되는 것이 아니라 FieldLogTrace 의 필드인 traceIdHolder 에 저장된다.
 */
@Slf4j
public class FieldLogTrace implements LogTrace{

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";
    /**
     * FieldLogTrace 는 싱글톤으로 등록된 스프링 빈이다. 이 객체의 인스턴스가 애플리케이션에 딱 1존재한다는 뜻이다.
     * 이렇게 하나만 있는 인스턴스의 FieldLogTrace.traceIdHolder 필드를 여러
     * 쓰레드가 동시에 접근하기 때문에 문제가 발생한다.
     * 실무에서 한번 나타나면 개발자를 가장 괴롭히는 문제도 바로 이러한 동시성 문제이다.
     */
    private TraceId traceIdHolder; //traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {} {}",traceId.getId(),addSpace(START_PREFIX,traceId.getLevel()),message);

        return new TraceStatus(traceId, startTimeMs, message);
    }

    private static String addSpace(String prefix, Integer level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }

    /**
     * syncTraceId()
         * TraceId 를 새로 만들거나 앞선 로그의 TraceId 를 참고해서 동기화하고, level 도 증가한다.
         * 최초 호출이면 TraceId 를 새로 만든다.
         * 직전 로그가 있으면 해당 로그의 TraceId 를 참고해서 동기화하고, level 도 하나 증가한다.
         * 결과를 traceIdHolder 에 보관한다.
     */
    private void syncTraceId() {
        if (traceIdHolder == null) {
            traceIdHolder = new TraceId();
        } else {
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }


    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}] {}{} time={}ms", traceId.getId(),
                    addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(),
                    resultTimeMs);
        } else {
            log.info("[{}] {}{} time={}ms ex={}", traceId.getId(),
                    addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,
                    e.toString());
        }
        releaseTraceId();
    }

    /**
     * releaseTraceId()
         * 메서드를 추가로 호출할 때는 level 이 하나 증가해야 하지만, 메서드 호출이 끝나면 level 이 하나 감소해야 한다.
         * releaseTraceId() 는 level 을 하나 감소한다.
         * 만약 최초 호출( level==0 )이면 내부에서 관리하는 traceId 를 제거한다.
     */
    private void releaseTraceId() {
        if (traceIdHolder.isFirstLevel()) {
            traceIdHolder = null; //destroy
        } else {
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }
}

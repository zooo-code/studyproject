package study.project.log.traceMonitor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import study.project.log.TraceId;
import study.project.log.logtrace.TraceStatus;

/**
 * 공개 메서드 로그 추적기에서 사용되는 공개 메서드는 다음 3가지이다.
 * begin(..)
 * end(..)
 * exception(..)
 */
@Slf4j
@Component
public class TraceLogic {

    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    /**
     * 로그를 시작한다.
     * 로그 메시지를 파라미터로 받아서 시작 로그를 출력한다.
     * 응답 결과로 현재 로그의 상태인 TraceStatus 를 반환한다.
     */
    public TraceStatus begin(String message){
        TraceId traceId = new TraceId();
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    /**
     * 로그를 정상 종료한다.
     * 파라미터로 시작 로그의 상태( TraceStatus )를 전달 받는다. 이 값을 활용해서 실행 시간을 계산하고,
     * 종료시에도 시작할 때와 동일한 로그 메시지를 출력할 수 있다.
     * 정상 흐름에서 호출한다
     */
    public void end(TraceStatus status) {
        complete(status, null);
    }

    /**
     * 로그를 예외 상황으로 종료한다.
     * TraceStatus , Exception 정보를 함께 전달 받아서 실행시간, 예외 정보를 포함한 결과 로그를 출력한다.
     * 예외가 발생했을 때 호출한다.
     */
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    /**
     * 비공개 메서드
     * end() , exception() , 의 요청 흐름을 한곳에서 편리하게 처리한다. 실행 시간을 측정하고 로그를 남긴다.
     */

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
    }

    /**
     * end() , exception() , 의 요청 흐름을 한곳에서 편리하게 처리한다. 실행 시간을 측정하고 로그를
     * 남긴다.
     * prefix: -->
     *  level 0:
     *  level 1: |-->
     *  level 2: | |-->
     * prefix: <--
     *  level 0:
     *  level 1: |<--
     *  level 2: | |<--
     * prefix: <X-
     *  level0:
     *  level 1: |<X-
     *  level2: | |<X-
     */
    /**
     * String은 소위 불변(immutable)객체라고 한다. String str1 = "abc";, String str2 = "def"; 2개의 String객체가 있을 때,
     * 만약 str1 + str2;와 같은 연산을 하게 되면 새로운 String을 생성한다.
     * 즉, String객체와 String객체를 더하는(+)행위는 메모리 할당과 메모리 해제를 발생시키며 더하는 연산이 많아진다면 성능적으로 좋지 않다.
     * 그래서 나온 것이 StringBuilder이다.
     * 이름만 봐도 String 잘 만들게(?) 생겼다.
     * StringBuilder는 String과 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존의 데이터에 더하는 방식을 사용하기 때문에 속도도 빠르며 상대적으로 부하가 적다.
     * 따라서 긴 문자열을 더하는 상황이 발생할 경우 StringBuilder를 적극적으로 사용하면 되겠다.
     */
    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
    }
}

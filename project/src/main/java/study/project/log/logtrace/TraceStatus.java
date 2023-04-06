package study.project.log.logtrace;

import lombok.Getter;
import study.project.log.TraceId;

@Getter
public class TraceStatus {
    /**
     * startTimeMs : 로그 시작시간이다. 로그 종료시 이 시작 시간을 기준으로 시작~종료까지 전체 수행
     * 시간을 구할 수 있다.
     * message : 시작시 사용한 메시지이다. 이후 로그 종료시에도 이 메시지를 사용해서 출력한다.
     */
    private TraceId traceId;

    private Long startTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }
}

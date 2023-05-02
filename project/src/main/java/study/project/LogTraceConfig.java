package study.project;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import study.project.log.logAop.LogAop;
import study.project.log.logtrace.LogTrace;
import study.project.log.logtrace.ThreadLocalLogTrace;


@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }

    @Bean
    public LogAop logAop(LogTrace logTrace){
//        return new FieldLogTrace();

        return new LogAop(logTrace);
    }
}

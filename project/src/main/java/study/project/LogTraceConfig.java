package study.project;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.project.log.logAop.LogAop;
import study.project.log.logtrace.LogTrace;


@Configuration
public class LogTraceConfig {

    @Bean
    public LogAop logTrace(LogTrace logTrace){
//        return new FieldLogTrace();
        return new LogAop(logTrace);
    }
}

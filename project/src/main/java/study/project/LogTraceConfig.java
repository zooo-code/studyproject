package study.project;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.project.log.logtrace.FieldLogTrace;
import study.project.log.logtrace.LogTrace;
import study.project.log.logtrace.ThreadLocalLogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
//        return new FieldLogTrace();
        return new ThreadLocalLogTrace();
    }
}

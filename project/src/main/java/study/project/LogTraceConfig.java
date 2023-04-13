package study.project;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import study.project.log.logtrace.FieldLogTrace;
import study.project.log.logtrace.LogTrace;

@Configuration
public class LogTraceConfig {

    @Bean
    public LogTrace logTrace(){
        return new FieldLogTrace();
    }
}

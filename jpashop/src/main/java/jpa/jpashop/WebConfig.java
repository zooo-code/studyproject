package jpa.jpashop;

import jpa.jpashop.argumentResolver.LoginMemberArgumentResolver;
import jpa.jpashop.interceptor.LogInterceptor;
import jpa.jpashop.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**","/*.ico","/error");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/", "/members/new", "/login", "/logout",
                        "/css/**", "/*.ico", "/error",
                        "/api/v1/members", "/api/v2/members","/api/v2/members/{id}"
                        ,"/api/v1/simple-orders", "/api/v2/simple-orders","/api/v3/simple-orders"
                        ,"/api/v2/orders", "/api/v3/orders","/api/v3.1/orders", "/api/v4/orders"
                        ,"/api/v5/orders"
                );
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver());
    }




}

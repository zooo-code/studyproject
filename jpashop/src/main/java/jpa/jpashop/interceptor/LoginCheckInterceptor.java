package jpa.jpashop.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        log.info("인증 체크 인터셉터 실행 {}", requestURI);
        /**
         * request.getSession(true)
             * 세션이 있으면 기존 세션을 반환한다.
             * 세션이 없으면 새로운 세션을 생성해서 반환한다.
         * request.getSession(false)
             * 세션이 있으면 기존 세션을 반환한다.
             * 세션이 없으면 새로운 세션을 생성하지 않는다. null 을 반환한다.
         */
        HttpSession session = request.getSession(false);
        log.info("{}",SessionConst.LOGIN_MEMBER);
        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            //로그인으로 redirect
            response.sendRedirect("/login?redirectURL=" + requestURI);
            return false;
        }
        return true;
    }

}

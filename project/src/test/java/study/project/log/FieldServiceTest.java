package study.project.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


@Slf4j
class FieldServiceTest {

    private FieldService fieldService = new FieldService();

    /**
     * 이번에는 sleep(100) 을 설정해서 thread-A 의 작업이 끝나기 전에 thread-B 가 실행되도록 해보자.
     * 참고로 FieldService.logic() 메서드는 내부에 sleep(1000) 으로 1초의 지연이 있다. 따라서 1초
     * 이후에 호출하면 순서대로 실행할 수 있다. 다음에 설정할 100(ms)는 0.1초이기 때문에 thread-A 의
     * 작업이 끝나기 전에 thread-B 가 실행된다.
     */
    @Test
    public void field () {
        //given
        log.info("main start");
        Runnable userA = () -> {
            fieldService.logic("userA");
        };
        Runnable userB = () -> {
            fieldService.logic("userB");
        };
        Thread threadA = new Thread(userA);
        threadA.setName("thread-A");
        Thread threadB = new Thread(userB);
        threadB.setName("thread-B");
        /**
         * > 이런 동시성 문제는 지역 변수에서는 발생하지 않는다. 지역 변수는 쓰레드마다 각각 다른 메모리 영역이
         * 할당된다.
         * > 동시성 문제가 발생하는 곳은 같은 인스턴스의 필드(주로 싱글톤에서 자주 발생), 또는 static 같은 공용
         * 필드에 접근할 때 발생한다.
         * > 동시성 문제는 값을 읽기만 하면 발생하지 않는다. 어디선가 값을 변경하기 때문에 발생한다.
         */
        threadA.start(); //A실행
//        sleep(2000); //동시성 문제 발생X
        sleep(100); //동시성 문제 발생O
        threadB.start(); //B실행
        sleep(3000); //메인 쓰레드 종료 대기
        log.info("main exit");
    }
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
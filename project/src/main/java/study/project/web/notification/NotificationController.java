package study.project.web.notification;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@Controller
//@RequiredArgsConstructor
public class NotificationController {

//    private final NotificationService notificationService;
//
//    @GetMapping(value = "/subscribe/{id}", produces = "text/event-stream")
//    public SseEmitter subscribe(@PathVariable Long id,
//                                @RequestHeader(value = "Last-Event-ID",
//                                        required = false, defaultValue = "") String lastEventId) {
//        return notificationService.subscribe(id, lastEventId);
//    }

}

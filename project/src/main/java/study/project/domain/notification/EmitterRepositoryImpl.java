package study.project.domain.notification;


import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.Map;

@Repository
public class EmitterRepositoryImpl implements EmitterRepository{
    @Override
    public SseEmitter save(String emitterId, SseEmitter sseEmitter) {
        return null;
    }

    @Override
    public void saveEventCache(String eventCacheId, Object event) {

    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByEmail(String email) {
        return null;
    }

    @Override
    public Map<String, SseEmitter> findAllEmitterStartWithByEmailInList(List emails) {
        return null;
    }

    @Override
    public Map<String, Object> findAllEventCacheStartWithByEmail(String email) {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public void deleteAllEmitterStartWithId(String email) {

    }

    @Override
    public void deleteAllEventCacheStartWithId(String email) {

    }
}

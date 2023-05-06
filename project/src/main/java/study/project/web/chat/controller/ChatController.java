package study.project.web.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import study.project.domain.chat.ChatRoom;
import study.project.domain.chat.service.ChatService;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ChatRoom createRoom(@RequestBody String name) {
        return chatService.createRoom(name);
    }

    @GetMapping
    public List<ChatRoom> findAllRoom() {
        return chatService.findAllRoom();
    }
}
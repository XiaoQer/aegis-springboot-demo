package io.aegis.demo.api;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

    private final AtomicLong sequence = new AtomicLong();
    private final List<Message> messages = new CopyOnWriteArrayList<>();

    @Value("${app.environment:local}")
    private String environment;

    @GetMapping("/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "service", "aegis-springboot-demo",
                "message", "Hello from Aegis DevOps Platform",
                "environment", environment,
                "timestamp", Instant.now().toString());
    }

    @GetMapping("/messages")
    public List<Message> messages() {
        return List.copyOf(messages);
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public Message create(@Valid @RequestBody CreateMessage request) {
        Message message = new Message(sequence.incrementAndGet(), request.content(), Instant.now());
        messages.add(message);
        return message;
    }

    public record CreateMessage(@NotBlank String content) {}

    public record Message(long id, String content, Instant createdAt) {}
}

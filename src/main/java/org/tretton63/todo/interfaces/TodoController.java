package org.tretton63.todo.interfaces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tretton63.todo.domain.TodoItem;
import org.tretton63.todo.interfaces.requests.NewTodoItemRequest;

import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    static final Logger log = LoggerFactory.getLogger(TodoController.class);
    private static final HashMap<Principal, List<TodoItem>> values = new HashMap<>();

    @GetMapping(
            value = "/",
            produces = "application/json")
    ResponseEntity<List<TodoItem>> AllTodos(Principal principal) {
        log.debug("All Todos: {}\n", principal.getName());

        return ResponseEntity.ok(values.get(principal));
    }

    @PostMapping(value = "/", consumes = "application/json")
    ResponseEntity<Void> NewTodoItem(@RequestBody NewTodoItemRequest value, Principal principal) {
        log.debug("Principal: {} Received Value: \"{}\"", principal.getName(), value.getValue());
        if (value.getValue().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if (values.containsKey(principal)) {
            Objects.requireNonNull(values.computeIfPresent(principal, (user, arr) -> arr)).add(new TodoItem(value.getValue()));
        } else {
            values.computeIfAbsent(principal, user -> new ArrayList<>()).add(new TodoItem(value.getValue()));
        }
        return ResponseEntity.created(
                URI.create("/todo/"+URLEncoder.encode(String.format("%s", value.getValue()), StandardCharsets.UTF_8))).build();
    }


    @GetMapping(value = "/{value}")
    ResponseEntity<TodoItem> GetSpecificTodoItem(
            @PathVariable String value,
            Principal principal
    ) {
        log.debug("Principal {}, Value {}", principal.getName(), value);

        List<TodoItem> items = values.get(principal);
        log.debug("Todo items {}", items.size());
        TodoItem item = items.stream()
                .filter(v -> v.getValue().equalsIgnoreCase(URLDecoder.decode(value, StandardCharsets.UTF_8)))
                .findFirst()
                .orElseThrow();

        log.debug("returning {}", item);
        return ResponseEntity.ok(item);
    }
}

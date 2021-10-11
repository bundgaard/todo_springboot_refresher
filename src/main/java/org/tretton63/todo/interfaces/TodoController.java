package org.tretton63.todo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tretton63.todo.domain.TodoItem;
import org.tretton63.todo.interfaces.requests.NewTodoItemRequest;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private HashMap<Principal, ArrayList<TodoItem>> values = new HashMap();

    @GetMapping(
            value = "/",
            produces = "application/json")
    public ResponseEntity<List<TodoItem>> Index(Principal principal) {
        System.out.printf("%s\n", principal.getName());

        return ResponseEntity.ok(values.get(principal));
    }

    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<Void> NewTodoItem(@RequestBody NewTodoItemRequest value, Principal principal) {
        System.out.printf("Principal: %s\nReceived Value: \"%s\"%n", principal.getName(), value.getValue());
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
                URI.create(URLEncoder.encode(String.format("%s", value.getValue()), StandardCharsets.UTF_8))).build();
    }
}

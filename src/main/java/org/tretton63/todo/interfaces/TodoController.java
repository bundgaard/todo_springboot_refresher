package org.tretton63.todo.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.tretton63.todo.domain.CacheValue;
import org.tretton63.todo.interfaces.requests.CacheValueDto;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/todo")
public class TodoController {

    private List<CacheValue> values = new ArrayList<>();

    @GetMapping(
            value = "/",
            produces = "application/json")
    public ResponseEntity<List<CacheValue>> Index() {
        return ResponseEntity.ok(values);
    }

    @PostMapping(
            value = "/",
            consumes = "application/json")
    public ResponseEntity<Void> AddValue(
            @RequestBody CacheValueDto value
    ) {
        System.out.printf("Received Value: \"%s\"%n", value);
        if (value.getValue().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        values.add(new CacheValue(value));
        return ResponseEntity.created(
                URI.create(
                        String.format("/%s", value)))
                .build();
    }
}

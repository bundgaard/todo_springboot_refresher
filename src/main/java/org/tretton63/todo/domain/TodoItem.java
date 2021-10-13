package org.tretton63.todo.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.tretton63.todo.tools.InstantDeserializer;

import java.time.Instant;


public class TodoItem {
    private String value;
    private boolean completed;
    @JsonDeserialize(using  = InstantDeserializer.class)
    private Instant createdAt;

    public TodoItem(String value) {
        this.value = value;
        this.createdAt = Instant.now();
    }

    public TodoItem(String value, Instant createdAt) {
        this.value = value;
        this.createdAt = createdAt;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }


}

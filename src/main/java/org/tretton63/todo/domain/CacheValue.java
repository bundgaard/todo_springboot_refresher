package org.tretton63.todo.domain;

import java.time.Instant;

public class CacheValue<T> {
    private T value;

    @Override
    public String toString() {
        return "CacheValue{" +
                "value=" + value +
                ", createdAt=" + createdAt +
                '}';
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    private Instant createdAt;

    public CacheValue(T t) {
        value = t;
        createdAt = Instant.now();
    }
}

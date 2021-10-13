package org.tretton63.todo.tools;

import java.time.Instant;

import static java.time.format.DateTimeFormatter.ISO_INSTANT;

public class InstantDeserializer extends com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer<Instant>{

    public InstantDeserializer() {
        super(INSTANT, ISO_INSTANT);
    }
}

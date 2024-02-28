package de.palberg.graphql.presenter;

import java.time.ZonedDateTime;
import java.util.List;

public record Element(
        String id,
        Type type,
        List<Attribute<?, ?>> attributes,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {
    public Element(String id, Type type, List<Attribute<?, ?>> attributes) {
        this(id, type, attributes, ZonedDateTime.now());
    }

    public Element(String id, Type type, List<Attribute<?, ?>> attributes, ZonedDateTime createdAt) {
        this(id, type, attributes, createdAt, ZonedDateTime.now());
    }
}

package de.palberg.graphql.presenter;

import java.util.List;

public record Type(
        String id,
        String name,
        String description,
        List<Property> properties
) {}

package de.palberg.graphql.presenter;

import java.util.List;
import java.util.Optional;

public sealed interface Property {

    String id();
    String name();
    String description();

    record TextProperty(String id, String name, String description, boolean autoTrim, Optional<Integer> maxLength) implements Property {}
    record IntegerProperty(String id, String name, String description, Optional<Integer> max, Optional<Integer> min) implements Property {}
    record BooleanProperty(String id, String name, String description) implements Property {}
    record LinkedElementProperty(String id, String name, String description, Optional<List<String>> typeRestrictionIds) implements Property {}
}

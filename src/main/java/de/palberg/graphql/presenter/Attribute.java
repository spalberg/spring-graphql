package de.palberg.graphql.presenter;

import java.util.Optional;

public sealed interface Attribute<P, V> {

    P property();
    Optional<V> value();

    record TextAttribute(Property.TextProperty property, Optional<TextValue> value) implements Attribute<Property.TextProperty, TextAttribute.TextValue> {
        public record TextValue(String value) {}
    }
    record IntegerAttribute(Property.IntegerProperty property, Optional<IntegerValue> value) implements Attribute<Property.IntegerProperty, IntegerAttribute.IntegerValue> {
        public record IntegerValue(int value) {}
    }
    record BooleanAttribute(Property.BooleanProperty property, Optional<BooleanValue> value) implements Attribute<Property.BooleanProperty, BooleanAttribute.BooleanValue> {
        public record BooleanValue(boolean value) {}
    }
    record LinkedElementAttribute(Property.LinkedElementProperty property, Optional<LinkedElementValue> value) implements Attribute<Property.LinkedElementProperty, LinkedElementAttribute.LinkedElementValue> {
        public record LinkedElementValue(String elementId, String typeId) {}
    }
}
package de.palberg.graphql.repository;

import de.palberg.graphql.presenter.Attribute.BooleanAttribute;
import de.palberg.graphql.presenter.Attribute.IntegerAttribute;
import de.palberg.graphql.presenter.Attribute.LinkedElementAttribute;
import de.palberg.graphql.presenter.Attribute.TextAttribute;
import de.palberg.graphql.presenter.Element;
import de.palberg.graphql.presenter.Property;
import de.palberg.graphql.presenter.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.time.ZonedDateTime.now;

@Service
public class FixtureRepository {

    private final Map<String, Property> properties;
    private final Map<String, Type> types;
    private final Map<String, Element> elements;

    public Optional<Property> findPropertyById(String id) {
        return Optional.ofNullable(properties.get(id));
    }

    public List<Property> findAllProperties() {
        return properties.values().stream().toList();
    }

    public List<Property> findPropertiesByIds(List<String> ids) {
        return ids.stream().map(this::findPropertyById).flatMap(Optional::stream).toList();
    }

    public Optional<Type> findTypeById(String id) {
        return Optional.ofNullable(types.get(id));
    }

    public List<Type> findAllTypes() {
        return types.values().stream().toList();
    }

    public List<Type> findTypesByIds(List<String> ids) {
        return ids.stream().map(this::findTypeById).flatMap(Optional::stream).toList();
    }

    public Optional<Element> findElementById(String id) {
        return Optional.ofNullable(elements.get(id));
    }

    public List<Element> findAllElements() {
        return elements.values().stream().toList();
    }

    public List<Element> findElementsByIds(List<String> ids) {
        return ids.stream().map(this::findElementById).flatMap(Optional::stream).toList();
    }

    public List<Element> findElementsByTypeId(String typeId) {
        return elements.values().stream().filter(e -> e.type().id().equals(typeId)).toList();
    }

    public FixtureRepository() {
        var nameProperty = new Property.TextProperty("name", "Name", "Ein vollständiger Name", false, Optional.of(255));
        var ageProperty = new Property.IntegerProperty("age", "Alter", "Das Alter", Optional.empty(), Optional.of(0));
        var isAliveProperty = new Property.BooleanProperty("isAlive", "Am Leben", "Ob die Person noch am Leben ist");
        var partnerProperty = new Property.LinkedElementProperty("partner", "Partner", "Partner der Person", Optional.of(List.of("person")));
        var hometownProperty = new Property.LinkedElementProperty("hometown", "Heimatstadt", "Heimatstadt der Person", Optional.of(List.of("town")));

        var town = new Type("town", "Stadt", "Eine Stadt", List.of(nameProperty));
        var person = new Type("person", "Person", "Eine Person", List.of(nameProperty, ageProperty, isAliveProperty, partnerProperty, hometownProperty));

        var hamburg = new Element("hamburg", town, List.of(new TextAttribute(nameProperty, Optional.of(new TextAttribute.TextValue("Hamburg")))), now().minusDays(2).plusMinutes(42));
        var darmstadt = new Element("darmstadt", town, List.of(new TextAttribute(nameProperty, Optional.of(new TextAttribute.TextValue("Darmstadt")))), now().minusDays(42));

        var bobId = "bob";
        var alice = new Element("alice", person, List.of(
                new TextAttribute(nameProperty, Optional.of(new TextAttribute.TextValue("Alice"))),
                new IntegerAttribute(ageProperty, Optional.of(new IntegerAttribute.IntegerValue(31))),
                new BooleanAttribute(isAliveProperty, Optional.of(new BooleanAttribute.BooleanValue(true))),
                new LinkedElementAttribute(partnerProperty, Optional.of(new LinkedElementAttribute.LinkedElementValue(bobId, person.id()))),
                new LinkedElementAttribute(hometownProperty, Optional.of(new LinkedElementAttribute.LinkedElementValue(hamburg.id(), town.id())))
        ));
        var bob = new Element(bobId, person, List.of(
                new TextAttribute(nameProperty, Optional.of(new TextAttribute.TextValue("Bob"))),
                new IntegerAttribute(ageProperty, Optional.of(new IntegerAttribute.IntegerValue(35))),
                new BooleanAttribute(isAliveProperty, Optional.of(new BooleanAttribute.BooleanValue(true))),
                new LinkedElementAttribute(partnerProperty, Optional.of(new LinkedElementAttribute.LinkedElementValue(alice.id(), person.id()))),
                new LinkedElementAttribute(hometownProperty, Optional.of(new LinkedElementAttribute.LinkedElementValue(hamburg.id(), town.id())))
        ));
        var eve = new Element("eve", person, List.of(
                new TextAttribute(nameProperty, Optional.of(new TextAttribute.TextValue("Eve"))),
                new IntegerAttribute(ageProperty, Optional.of(new IntegerAttribute.IntegerValue(86))),
                new BooleanAttribute(isAliveProperty, Optional.of(new BooleanAttribute.BooleanValue(false))),
                new LinkedElementAttribute(partnerProperty, Optional.empty()),
                new LinkedElementAttribute(hometownProperty, Optional.of(new LinkedElementAttribute.LinkedElementValue(darmstadt.id(), town.id())))
        ));

        properties = Stream.of(nameProperty, ageProperty, isAliveProperty, partnerProperty, hometownProperty).collect(Collectors.toMap(Property::id, Function.identity()));
        types = Stream.of(town, person).collect(Collectors.toMap(Type::id, Function.identity()));
        elements = Stream.of(hamburg, darmstadt, alice, bob, eve).collect(Collectors.toMap(Element::id, Function.identity()));
    }
}

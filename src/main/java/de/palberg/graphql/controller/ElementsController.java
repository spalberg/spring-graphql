package de.palberg.graphql.controller;

import de.palberg.graphql.presenter.Attribute;
import de.palberg.graphql.presenter.Element;
import de.palberg.graphql.presenter.Property;
import de.palberg.graphql.presenter.Type;
import de.palberg.graphql.repository.FixtureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class ElementsController {

    private static final Logger log = LoggerFactory.getLogger(ElementsController.class);

    private final FixtureRepository fixtureRepository;

    public ElementsController(FixtureRepository fixtureRepository) {this.fixtureRepository = fixtureRepository;}

    @QueryMapping
    public List<Property> properties() {
        return fixtureRepository.findAllProperties();
    }

    @QueryMapping
    public Optional<Property> propertyById(@Argument String id) {
        return fixtureRepository.findPropertyById(id);
    }

    @QueryMapping
    public List<Type> types() {
        return fixtureRepository.findAllTypes();
    }

    @QueryMapping
    public Optional<Type> typeById(@Argument String id) {
        return fixtureRepository.findTypeById(id);
    }

    @QueryMapping
    public List<Element> elements(@Argument Optional<String> typeId) {
        return typeId
                .map(fixtureRepository::findElementsByTypeId)
                .orElseGet(fixtureRepository::findAllElements);
    }

    @QueryMapping
    public Optional<Element> elementById(@Argument String id) {
        return fixtureRepository.findElementById(id);
    }

    @BatchMapping
    public Map<Attribute.LinkedElementAttribute.LinkedElementValue, Element> element(List<Attribute.LinkedElementAttribute.LinkedElementValue> values) {
        var elements = fixtureRepository.findElementsByIds(values.stream().map(Attribute.LinkedElementAttribute.LinkedElementValue::elementId).toList());
        var result = new HashMap<Attribute.LinkedElementAttribute.LinkedElementValue, Element>();
        for (var value : values) {
            elements.stream().filter(e -> e.id().equals(value.elementId())).findFirst().ifPresent(element -> {
                result.put(value, element);
            });
        }
        return result;
    }
}


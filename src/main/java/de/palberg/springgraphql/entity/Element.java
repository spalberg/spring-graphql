package de.palberg.springgraphql.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Builder
@ToString
public class Element {
    @Id
    @Getter
    private ObjectId id;

    @Getter
    private Type type;

    @Getter
    @Setter
    private String name;

    @PersistenceCreator
    public Element(ObjectId id, Type type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public enum Type {
        BIER,
        BUCH,
        BEGUENSTIGTER,
        MITARBEITER,
    }
}

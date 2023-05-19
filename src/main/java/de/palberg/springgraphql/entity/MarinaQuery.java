package de.palberg.springgraphql.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Builder
@ToString
public class MarinaQuery {
    @Id
    @Getter
    private ObjectId id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private List<Element.Type> filterTypes;

    public MarinaQuery(ObjectId id, String name, List<Element.Type> filterTypes) {
        this.id = id;
        this.name = name;
        this.filterTypes = filterTypes;
    }
}

package de.palberg.springgraphql.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document
@Builder
@ToString
public class Overview {

    @Id
    @Getter
    private ObjectId id;

    @Getter
    @Setter
    private String name;

    @DocumentReference(lazy = true)
    @Getter
    @Setter
    private MarinaQuery query;

    @Getter
    @Setter
    private ObjectId queryId;

    public Overview(ObjectId id, String name, MarinaQuery query, ObjectId queryId) {
        this.id = id;
        this.name = name;
        this.query = query;
        this.queryId = queryId;
    }
}

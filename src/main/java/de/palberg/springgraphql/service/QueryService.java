package de.palberg.springgraphql.service;

import de.palberg.springgraphql.entity.Element;
import de.palberg.springgraphql.entity.MarinaQuery;
import de.palberg.springgraphql.repository.ElementRepository;
import de.palberg.springgraphql.repository.MarinaQueryRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.stereotype.Component;

@Component
public class QueryService {

    private final MarinaQueryRepository marinaQueryRepository;
    private final ElementRepository elementRepository;
    private final MongoTemplate mongoTemplate;

    public QueryService(MarinaQueryRepository marinaQueryRepository, ElementRepository elementRepository, MongoTemplate mongoTemplate) {
        this.marinaQueryRepository = marinaQueryRepository;
        this.elementRepository = elementRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public QueryResult execute(MarinaQuery query, String search, int size) {
        var filterTypes = query.getFilterTypes();
        mongoTemplate.aggregate(Aggregation.newAggregation(

        ), Element.class, QueryResult.class);
        return new QueryResult();
    }

    record QueryResult() {
    }
}

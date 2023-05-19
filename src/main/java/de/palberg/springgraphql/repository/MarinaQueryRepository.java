package de.palberg.springgraphql.repository;

import de.palberg.springgraphql.entity.MarinaQuery;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.stream.Stream;

public interface MarinaQueryRepository extends MongoRepository<MarinaQuery, ObjectId> {

    Stream<MarinaQuery> streamAllByIdIn(Collection<ObjectId> ids);
}

package de.palberg.springgraphql.repository;

import de.palberg.springgraphql.entity.Overview;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OverviewRepository extends MongoRepository<Overview, ObjectId> {

    Optional<Overview> findByName(String name);
}

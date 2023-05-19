package de.palberg.springgraphql.repository;

import de.palberg.springgraphql.entity.Element;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;


public interface ElementRepository extends MongoRepository<Element, ObjectId> {

    Iterable<Element> findAllByIdIn(Collection<ObjectId> ids);
}

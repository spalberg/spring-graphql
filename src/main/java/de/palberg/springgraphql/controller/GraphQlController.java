package de.palberg.springgraphql.controller;

import de.palberg.springgraphql.entity.Element;
import de.palberg.springgraphql.entity.MarinaQuery;
import de.palberg.springgraphql.entity.Overview;
import de.palberg.springgraphql.repository.ElementRepository;
import de.palberg.springgraphql.repository.MarinaQueryRepository;
import de.palberg.springgraphql.repository.OverviewRepository;
import org.bson.types.ObjectId;
import org.dataloader.DataLoader;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class GraphQlController {

    private final OverviewRepository overviewRepository;
    private final MarinaQueryRepository marinaQueryRepository;
    private final ElementRepository elementRepository;

    GraphQlController(OverviewRepository overviewRepository, MarinaQueryRepository marinaQueryRepository,
            ElementRepository elementRepository, BatchLoaderRegistry batchLoaderRegistry) {
        this.overviewRepository = overviewRepository;
        this.marinaQueryRepository = marinaQueryRepository;
        this.elementRepository = elementRepository;
        batchLoaderRegistry.forTypePair(ObjectId.class, MarinaQuery.class).registerMappedBatchLoader((ids, env) -> {
            var x = marinaQueryRepository.streamAllByIdIn(ids);
            var results = x.collect(Collectors.toMap(MarinaQuery::getId, Function.identity()));
            return Mono.just(results);
        });
    }

    @QueryMapping
    Flux<Overview> overviews() {
        return Flux.fromIterable(overviewRepository.findAll());
    }

    @SchemaMapping
    CompletableFuture<MarinaQuery> query(Overview overview, DataLoader<ObjectId, MarinaQuery> loader) {
        return loader.load(overview.getQueryId());
    }

    @QueryMapping
    Mono<Overview> overview(@Argument Optional<ObjectId> id, @Argument Optional<String> name) {
        return Mono.justOrEmpty(
                id.map(overviewRepository::findById)
                        .orElseGet(() -> name.map(overviewRepository::findByName).flatMap(Function.identity())));
    }
}

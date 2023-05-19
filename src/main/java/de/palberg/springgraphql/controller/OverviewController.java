package de.palberg.springgraphql.controller;

import de.palberg.springgraphql.entity.Overview;
import de.palberg.springgraphql.repository.OverviewRepository;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OverviewController {
    private final OverviewRepository overviewRepository;

    OverviewController(OverviewRepository overviewRepository) {
        this.overviewRepository = overviewRepository;
    }

    @GetMapping("/overviews")
    Iterable<Overview> all() {
        return overviewRepository.findAll();
    }

    @GetMapping("/overviews/{id}")
    ResponseEntity<Overview> byId(@PathVariable ObjectId id) {
        return ResponseEntity.of(overviewRepository.findById(id));
    }
}

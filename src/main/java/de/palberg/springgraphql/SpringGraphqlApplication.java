package de.palberg.springgraphql;

import de.palberg.springgraphql.entity.Element;
import de.palberg.springgraphql.entity.MarinaQuery;
import de.palberg.springgraphql.entity.Overview;
import de.palberg.springgraphql.repository.ElementRepository;
import de.palberg.springgraphql.repository.MarinaQueryRepository;
import de.palberg.springgraphql.repository.OverviewRepository;
import net.datafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringGraphqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringGraphqlApplication.class, args);
    }

    @Bean
    ApplicationListener<ApplicationReadyEvent> onApplicationReady(OverviewRepository overviewRepository, MarinaQueryRepository marinaQueryRepository, ElementRepository elementRepository) {
        return event -> {
            var faker = new Faker(Locale.GERMANY);
            elementRepository.deleteAll();
            marinaQueryRepository.deleteAll();
            overviewRepository.deleteAll();

            var biere = MarinaQuery.builder().name("Biere").filterTypes(List.of(Element.Type.BIER)).build();
            marinaQueryRepository.save(biere);
            overviewRepository.save(Overview.builder().name("Biere").query(biere).queryId(biere.getId()).build());

            var bücher = MarinaQuery.builder().name("Bücker").filterTypes(List.of(Element.Type.BUCH)).build();
            marinaQueryRepository.save(bücher);
            overviewRepository.save(Overview.builder().name("bücher").query(bücher).queryId(bücher.getId()).build());

            var begünstigte = MarinaQuery.builder().name("Begünstigte").filterTypes(List.of(Element.Type.BEGUENSTIGTER)).build();
            marinaQueryRepository.save(begünstigte);
            overviewRepository.save(Overview.builder().name("Begünstigte").query(begünstigte).queryId(begünstigte.getId()).build());

            var mitarbeiter = MarinaQuery.builder().name("Mitarbeiter").filterTypes(List.of(Element.Type.BEGUENSTIGTER)).build();
            marinaQueryRepository.save(mitarbeiter);
            overviewRepository.save(Overview.builder().name("Mitarbeiter").query(mitarbeiter).queryId(mitarbeiter.getId()).build());

            elementRepository.saveAll(
                    Stream.generate(() -> {
                                var beer = faker.beer();
                                return Element.builder()
                                        .type(Element.Type.BIER)
                                        .name(beer.name() + " (" + beer.brand() + ", " + beer.style() + ")")
                                        .build();
                            }
                    ).limit(faker.number().numberBetween(150, 250)).toList()
            );
            elementRepository.saveAll(
                    Stream.generate(() -> {
                                var book = faker.book();
                                return Element.builder()
                                        .type(Element.Type.BUCH)
                                        .name(book.title() + " (" + book.author() + ", " + book.genre() + ")")
                                        .build();
                            }
                    ).limit(faker.number().numberBetween(150, 250)).toList()
            );
            elementRepository.saveAll(
                    Stream.generate(() -> Element.builder()
                            .type(Element.Type.BEGUENSTIGTER)
                            .name(faker.name().fullName())
                            .build()
                    ).limit(faker.number().numberBetween(150, 250)).toList()
            );
            elementRepository.saveAll(
                    Stream.generate(() -> Element.builder()
                            .type(Element.Type.MITARBEITER)
                            .name(faker.breakingBad().character())
                            .build()
                    ).limit(faker.number().numberBetween(150, 250)).toList()
            );
        };
    }
}

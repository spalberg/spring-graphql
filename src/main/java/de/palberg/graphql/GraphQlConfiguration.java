package de.palberg.graphql;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.Value;
import graphql.schema.*;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@Configuration
public class GraphQlConfiguration {

    private static final Logger log = LoggerFactory.getLogger(GraphQlConfiguration.class);

    @Bean
    GraphQlSourceBuilderCustomizer inspectionCustomizer() {
        return source -> source.inspectSchemaMappings(report -> log.info(report.toString()));
    }

    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer() {
        var dateTimeScalar = GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("A custom scalar that handles DateTime")
                .coercing(new Coercing<ZonedDateTime, String>() {
                    @Override
                    public String serialize(@NotNull Object dataFetcherResult, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingSerializeException {
                        if(dataFetcherResult instanceof ZonedDateTime dt) {
                            return dt.format(DateTimeFormatter.ISO_DATE_TIME);
                        }
                        throw new CoercingSerializeException();
                    }

                    @Override
                    public ZonedDateTime parseValue(@NotNull Object input, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseValueException {
                        if(input instanceof String dts) {
                            return ZonedDateTime.parse(dts, DateTimeFormatter.ISO_DATE_TIME);
                        }
                        throw new CoercingParseValueException();
                    }

                    @Override
                    public ZonedDateTime parseLiteral(@NotNull Value<?> input, @NotNull CoercedVariables variables, @NotNull GraphQLContext graphQLContext, @NotNull Locale locale) throws CoercingParseLiteralException {
                        // TODO handle
                        log.info(input.toString());
                        throw new CoercingParseLiteralException();
                    }
                })
                .build();
        return wiringBuilder -> wiringBuilder
                .scalar(dateTimeScalar);
    }
}
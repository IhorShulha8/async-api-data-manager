package com.ihorshulha.stockinfo.config;

import com.ihorshulha.stockinfo.exception.RestTemplateResponseErrorHandler;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.client.RestTemplate;


@Configuration
@EnableR2dbcRepositories
public class ConfigurationService {

    @Value("${api.database.host}")
    private String host;
    @Value("${api.database.port}")
    private int port;
    @Value("${api.database.name}")
    private String name;
    @Value("${api.database.username}")
    private String username;
    @Value("${api.database.password}")
    private String password;

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory) {
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .namedParameters(true)
                .build();
    }

    @Bean
    public R2dbcEntityTemplate r2dbcEntityTemplate(ConnectionFactory connectionFactory) {
        return new R2dbcEntityTemplate(connectionFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new RestTemplateResponseErrorHandler());
        return restTemplate;
    }
}

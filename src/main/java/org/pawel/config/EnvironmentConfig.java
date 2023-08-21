package org.pawel.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.net.URI;
import java.util.Optional;

@Getter
@Configuration
@PropertySource("classpath:environment.properties")
public class EnvironmentConfig {

    @Value("${apilayer.base.uri}")
    private URI apilayerBaseUri;

    @Value("${apilayer.apikey}")
    private Optional<String> apilayerKey;

}


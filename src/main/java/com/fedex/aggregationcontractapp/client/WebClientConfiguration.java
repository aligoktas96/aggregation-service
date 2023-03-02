package com.fedex.aggregationcontractapp.client;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;

@Configuration
public class WebClientConfiguration {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        DefaultUriBuilderFactory uriBuilderFactory = new DefaultUriBuilderFactory("http://localhost:4000/");

        return builder
                .uriTemplateHandler(uriBuilderFactory)
                .setConnectTimeout(Duration.ofMillis(5000))
                .setReadTimeout(Duration.ofMillis(5000))
                .build();
    }


}

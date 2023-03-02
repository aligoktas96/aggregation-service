package com.fedex.aggregationcontractapp.client;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class CustomClient {

    private final RestTemplate client;

    @Async
    public <T> T getRequest(String endpoint, String queryParam, String value, Class<T> clazz) {
        return client.getForObject(format("/%s?%s=%s", endpoint, queryParam, value), clazz);
    }
}

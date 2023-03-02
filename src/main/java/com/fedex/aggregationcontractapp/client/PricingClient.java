package com.fedex.aggregationcontractapp.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class PricingClient {

    private final CustomClient customClient;

    public CompletableFuture<Double> request(String code) {
        return CompletableFuture.completedFuture(customClient.getRequest("pricing", "countryCode", code, Double.class));
    }
}

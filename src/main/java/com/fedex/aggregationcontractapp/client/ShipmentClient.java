package com.fedex.aggregationcontractapp.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class ShipmentClient {
    private final CustomClient customClient;

    public CompletableFuture<String[]> request(String code) {
        return CompletableFuture.completedFuture(customClient
                .getRequest("shipment-products", "orderNumber", code, String[].class));
    }
}

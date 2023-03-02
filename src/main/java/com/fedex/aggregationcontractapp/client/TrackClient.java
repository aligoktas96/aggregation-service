package com.fedex.aggregationcontractapp.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@RequiredArgsConstructor
public class TrackClient {

    private final CustomClient customClient;

    public CompletableFuture<String> request(String code) {
        return CompletableFuture.completedFuture(customClient.getRequest("track-status", "orderNumber", code, String.class));
    }
}

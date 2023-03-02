package com.fedex.aggregationcontractapp.service;

import com.fedex.aggregationcontractapp.client.PricingClient;
import com.fedex.aggregationcontractapp.client.ShipmentClient;
import com.fedex.aggregationcontractapp.client.TrackClient;
import com.fedex.aggregationcontractapp.controller.response.AggregationResponse;
import com.fedex.aggregationcontractapp.controller.response.CountryCodeResponse;
import com.fedex.aggregationcontractapp.controller.response.ShipmentResponse;
import com.fedex.aggregationcontractapp.controller.response.TrackResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AggregationService {

    private final ShipmentClient shipmentClient;
    private final TrackClient trackClient;
    private final PricingClient pricingClient;

    public AggregationResponse getAggregation(List<String> shipmentOrderId, List<String> trackOrderId, List<String> countryCode) {

        CompletableFuture<Stream<ShipmentResponse>> shipmentResponse = CompletableFuture
                .supplyAsync(() -> Optional.ofNullable(shipmentOrderId)
                        .orElseGet(List::of).stream()
                        .map(this::getOrderNumber));

        CompletableFuture<Stream<TrackResponse>> trackResponse = CompletableFuture
                .supplyAsync(() -> Optional.ofNullable(trackOrderId)
                        .orElseGet(List::of).stream()
                        .map(this::getNumber));

        CompletableFuture<Stream<CountryCodeResponse>> pricingResponse = CompletableFuture
                .supplyAsync(() -> Optional.ofNullable(countryCode)
                        .orElseGet(List::of).stream()
                        .map(this::getCountryCode));

        Map<String, String[]> shipments = shipmentResponse
                .join()
                .collect(Collectors.toMap(ShipmentResponse::getOrderNumber, ShipmentResponse::getResponse));

        Map<String, String> tracks = trackResponse.join()
                .collect(Collectors.toMap(TrackResponse::getOrderNumber, TrackResponse::getResponse));

        Map<String, Double> pricing = pricingResponse.join()
                .collect(Collectors.toMap(CountryCodeResponse::getCountryCode, CountryCodeResponse::getResponse));

        return AggregationResponse.builder()
                .shipments(shipments)
                .track(tracks)
                .pricing(pricing)
                .build();
    }


    private CountryCodeResponse getCountryCode(String code) {

        Double response = pricingClient.request(code).join();
        return CountryCodeResponse.builder()
                .countryCode(code)
                .response(response)
                .build();
    }

    private TrackResponse getNumber(String orderNumber) {
        String response = trackClient.request(orderNumber).join();

        return TrackResponse.builder()
                .orderNumber(orderNumber)
                .response(response)
                .build();
    }

    private ShipmentResponse getOrderNumber(String orderNumber) {
        String[] response = shipmentClient.request(orderNumber).join();

        return ShipmentResponse.builder()
                .orderNumber(orderNumber)
                .response(response)
                .build();
    }
}


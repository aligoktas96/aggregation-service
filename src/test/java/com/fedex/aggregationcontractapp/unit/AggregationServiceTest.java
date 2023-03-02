package com.fedex.aggregationcontractapp.unit;

import com.fedex.aggregationcontractapp.client.PricingClient;
import com.fedex.aggregationcontractapp.client.ShipmentClient;
import com.fedex.aggregationcontractapp.client.TrackClient;
import com.fedex.aggregationcontractapp.controller.response.AggregationResponse;
import com.fedex.aggregationcontractapp.service.AggregationService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AggregationServiceTest {

    @Mock
    private ShipmentClient shipmentClient;

    @Mock
    private TrackClient trackClient;

    @Mock
    private PricingClient pricingClient;

    @InjectMocks
    private AggregationService aggregationService;

    @Test
    void testGetAggregationReturnsCorrectData() {
        // Arrange
        List<String> shipmentOrderId = List.of("123", "456");
        List<String> trackOrderId = List.of("789", "012");
        List<String> countryCode = List.of("US", "CA");
        when(shipmentClient.request("123")).thenReturn(CompletableFuture.completedFuture(new String[]{"Item 1"}));
        when(shipmentClient.request("456")).thenReturn(CompletableFuture.completedFuture(new String[]{"Item 2"}));
        when(trackClient.request("789")).thenReturn(CompletableFuture.completedFuture("Tracking Info 1"));
        when(trackClient.request("012")).thenReturn(CompletableFuture.completedFuture("Tracking Info 2"));
        when(pricingClient.request("US")).thenReturn(CompletableFuture.completedFuture(10.0));
        when(pricingClient.request("CA")).thenReturn(CompletableFuture.completedFuture(15.0));

        // Act
        AggregationResponse aggregationResponse = aggregationService.getAggregation(shipmentOrderId, trackOrderId, countryCode);

        // Assert
        assertThat(aggregationResponse.getShipments()).hasSize(2);
        assertThat(aggregationResponse.getShipments().get("123")).containsExactly("Item 1");
        assertThat(aggregationResponse.getShipments().get("456")).containsExactly("Item 2");
        assertThat(aggregationResponse.getTrack()).hasSize(2);
        assertThat(aggregationResponse.getTrack().get("789")).isEqualTo("Tracking Info 1");
        assertThat(aggregationResponse.getTrack().get("012")).isEqualTo("Tracking Info 2");
        assertThat(aggregationResponse.getPricing()).hasSize(2);
        assertThat(aggregationResponse.getPricing().get("US")).isEqualTo(10.0);
        assertThat(aggregationResponse.getPricing().get("CA")).isEqualTo(15.0);
    }

    @Test
    void testGetAggregationReturnsEmptyResponseWhenInputIsNull() {
        // Act
        AggregationResponse aggregationResponse = aggregationService.getAggregation(null, null, null);

        // Assert
        assertThat(aggregationResponse.getShipments()).isEmpty();
        assertThat(aggregationResponse.getTrack()).isEmpty();
        assertThat(aggregationResponse.getPricing()).isEmpty();
    }

    @Test
    void testGetAggregationReturnsEmptyResponseWhenInputIsEmpty() {
        // Act
        AggregationResponse aggregationResponse = aggregationService.getAggregation(List.of(), List.of(), List.of());

        // Assert
        assertThat(aggregationResponse.getShipments()).isEmpty();
        assertThat(aggregationResponse.getTrack()).isEmpty();
        assertThat(aggregationResponse.getPricing()).isEmpty();
    }

}

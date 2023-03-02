package com.fedex.aggregationcontractapp.controller;

import com.fedex.aggregationcontractapp.controller.response.AggregationResponse;
import com.fedex.aggregationcontractapp.service.AggregationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/aggregation")
@RequiredArgsConstructor
public
class AggregationController {

    private final AggregationService aggregationService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AggregationResponse> aggregate(
            @RequestParam(name = "shipmentsOrderNumbers", required = false) List<String> shipmentsOrderNumbers,
            @RequestParam(name = "trackOrderNumbers", required = false) List<String> trackOrderNumbers,
            @RequestParam(name = "pricingCountryCodes", required = false) List<String> pricingCountryCodes) {
        return ResponseEntity.ok(aggregationService.getAggregation(shipmentsOrderNumbers, trackOrderNumbers, pricingCountryCodes));

    }
}

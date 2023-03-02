package com.fedex.aggregationcontractapp.controller.response;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AggregationResponse {
    private Map<String, String[]> shipments;
    private Map<String, String> track;
    private Map<String, Double> pricing;
}

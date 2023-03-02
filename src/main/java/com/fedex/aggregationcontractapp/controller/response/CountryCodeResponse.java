package com.fedex.aggregationcontractapp.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CountryCodeResponse {
    private String countryCode;
    private Double response;
}

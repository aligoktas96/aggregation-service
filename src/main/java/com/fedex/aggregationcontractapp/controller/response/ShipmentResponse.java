package com.fedex.aggregationcontractapp.controller.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShipmentResponse {
    private String orderNumber;
    private String[] response;
}

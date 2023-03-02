package com.fedex.aggregationcontractapp.integration;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.aggregationcontractapp.controller.AggregationController;
import com.fedex.aggregationcontractapp.controller.response.AggregationResponse;
import com.fedex.aggregationcontractapp.service.AggregationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AggregationController.class)
public class AggregationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AggregationService aggregationService;

    @Test
    void aggregate_ReturnsAggregationResponse_WhenValidRequest() throws Exception {
        // Setup
        AggregationResponse expectedResponse = AggregationResponse.builder().build();

        given(aggregationService.getAggregation(anyList(), anyList(), anyList())).willReturn(expectedResponse);

        // Execute and Assert
        mockMvc.perform(get("/aggregation")
                        .param("shipmentsOrderNumbers", "109347263", "109347262")
                        .param("trackOrderNumbers", "109347262", "109347263")
                        .param("pricingCountryCodes", "US", "CA"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(expectedResponse)));
    }
}

package com.example.assessment.controller;
import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.BaseResponse;
import com.example.assessment.utils.Mappers;
import com.example.assessment.utils.impl.MB2CResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;


public class MB2CResponseTest {

    @Mock
    private B2CRequest mockB2CRequest;

    @Mock
    private Mappers mockMappers;

    private MB2CResponse mb2CResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize MB2CResponse with mock B2CRequest
        mb2CResponse = new MB2CResponse(mockB2CRequest);
    }

    @Test
    void testGenerateResponse() {
        // Expected response JSON
        String expectedJson = "{\"responseCode\":0,\"responseMessage\":\"Success\"}";
        BaseResponse baseResponse=new BaseResponse();
        baseResponse.setResponseCode(0);
        baseResponse.setResponseMessage("Success");

        // Mock Mappers behavior
        Mappers mappers = new Mappers();
        String actualJson = mappers.objectToJson(baseResponse);

        // Validate response
        assertEquals(expectedJson, actualJson);
    }

    @Test
    void testGetMockResponse() {
        String response = mb2CResponse.getMockResponse();

        // Ensure it is not null or empty
        assertNotNull(response);
        assertTrue(response.contains("\"responseCode\":0"));
        assertTrue(response.contains("\"responseMessage\":\"Success\""));
    }
}

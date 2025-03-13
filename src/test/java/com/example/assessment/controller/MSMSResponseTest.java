package com.example.assessment.controller;

import com.example.assessment.dto.SMSRequest;
import com.example.assessment.utils.RandomGenerator;
import com.example.assessment.utils.impl.MSMSResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



public class MSMSResponseTest {

    @Mock
    private SMSRequest mockSmsRequest;

    @Mock
    private RandomGenerator randomGenerator;

    private MSMSResponse msmsResponse;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock SMSRequest behavior
        when(mockSmsRequest.isSender()).thenReturn(true);
        when(mockSmsRequest.getMobileNumber()).thenReturn("254712345678");
        when(mockSmsRequest.getAmount()).thenReturn(100.0);
         when(mockSmsRequest.getTelcos()).thenReturn("M-PESA");


        // Stub RandomGenerator
        mockStatic(RandomGenerator.class);
        when(RandomGenerator.generateTransactionCode()).thenReturn("TX12345678");

        // Initialize the class with mock data
        msmsResponse = new MSMSResponse(mockSmsRequest);
    }

    @Test
    void testGenerateResponseForMpesa() {
       
        String response = msmsResponse.generateResponse();

        // Assert response contains expected values
        assertTrue(response.contains("\"responseCode\":0"));
        assertTrue(response.contains("\"responseMessage\":\"Success\""));
        assertTrue(response.contains("254712345678"));
        assertTrue(response.contains("100.0"));
        assertTrue(response.contains("M-PESA"));
        assertTrue(response.contains("TX12345678"));
    }

}

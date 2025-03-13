package com.example.assessment.utils;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class MockB2CResponse<T> {
    private B2CRequest b2CRequest;
    private Mappers mappers;

    // Constructor
    public MockB2CResponse(B2CRequest b2CRequest) {
        this.b2CRequest = b2CRequest;
     ;
    }

    // Abstract method for customization
    public abstract T generateResponse();

    // Default method to return the stored mock response
    public T getMockResponse()  {

        return generateResponse() ;
    }

    // Setter for updating mock response
    public void setMockResponse(B2CRequest b2CRequest) {
        this.b2CRequest = b2CRequest;
    }
}


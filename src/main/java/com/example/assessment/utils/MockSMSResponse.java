package com.example.assessment.utils;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.SMSRequest;

public abstract class MockSMSResponse<T> {
    private SMSRequest smsRequest;
    private Mappers mappers;

    // Constructor
    public MockSMSResponse(SMSRequest smsRequest) {
        this.smsRequest = smsRequest;
     ;
    }

    // Abstract method for customization
    public abstract T generateResponse();

    // Default method to return the stored mock response
    public T getMockResponse()  {

        return generateResponse() ;
    }

    // Setter for updating mock response
    public void setMockResponse(SMSRequest smsRequest) {
        this.smsRequest = smsRequest;
    }
}


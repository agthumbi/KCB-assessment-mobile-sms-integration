package com.example.assessment.utils.impl;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.BaseResponse;
import com.example.assessment.utils.Mappers;
import com.example.assessment.utils.MockB2CResponse;


public class MB2CResponse extends MockB2CResponse<String> {


    public MB2CResponse(B2CRequest b2CRequest) {
        super(b2CRequest);
    }

    @Override
    public String generateResponse() {
        Mappers mappers = new Mappers();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(0);
        baseResponse.setResponseMessage("Success");
        return mappers.objectToJson(baseResponse);

    }
}

package com.example.assessment.utils.impl;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.BaseResponse;
import com.example.assessment.dto.SMSRequest;
import com.example.assessment.utils.*;

import java.util.Date;


public class MSMSResponse extends MockSMSResponse<String> {

    private SMSRequest smsRequest;

    public MSMSResponse(SMSRequest smsRequest) {
        super(smsRequest);
        this.smsRequest = smsRequest;
    }

    @Override
    public String generateResponse() {
        Mappers mappers = new Mappers();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setResponseCode(0);
        baseResponse.setResponseMessage("Success");
        String message = "";
        if (smsRequest.isSender()) {
            message = ConstanUtils.telcoMessageSender;
            message = message.replace("[mobile]", smsRequest.getMobileNumber());
            message = message.replace("[date]", new Date().toString());
            message = message.replace("[amount]", String.valueOf(smsRequest.getAmount()));
            message = message.replace("[txnid]", RandomGenerator.generateTransactionCode());
            message = message.replace("[telco]", smsRequest.getTelcos());

        } else {
            message = ConstanUtils.telcoMessageReciever;
            message = message.replace("[mobile]", smsRequest.getMobileNumber());
            message = message.replace("[date]", new Date().toString());
            message = message.replace("[amount]", String.valueOf(smsRequest.getAmount()));
            message = message.replace("[txnid]", RandomGenerator.generateTransactionCode());
            message = message.replace("[telco]", smsRequest.getTelcos());
        }
        baseResponse.setData(message);
        return mappers.objectToJson(baseResponse);

    }
}

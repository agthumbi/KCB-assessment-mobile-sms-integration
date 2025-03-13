package com.example.assessment.services;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.dto.BaseResponse;
import com.example.assessment.dto.SMSRequest;
import com.example.assessment.models.B2C;
import com.example.assessment.models.SMS_Notifications;
import com.example.assessment.repos.B2CRepo;

import com.example.assessment.repos.SMSRepo;
import com.example.assessment.utils.ConstanUtils;
import com.example.assessment.utils.impl.MB2CResponse;
import com.example.assessment.utils.Mappers;
import com.example.assessment.utils.impl.MSMSResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Service
@Validated
@AllArgsConstructor
public class B2CService {
    private final B2CRepo b2CRepo;
    private final SMSRepo smsRepo;
    private Mappers mappers;
    private static final Logger logger = LoggerFactory.getLogger(B2CService.class);

    public ResponseEntity<?> getB2CRequest(@Valid B2CRequest b2CRequest) {
        SMSRequest smsRequest = new SMSRequest();
        BaseResponse baseResponse = new BaseResponse();


        B2C b2C = new B2C();
        b2C.setAmount(b2CRequest.getAmount());
        b2C.setTelco(b2CRequest.getTelco());
        b2C.setNarration(b2CRequest.getNarration());
        b2C.setReciepientMobileNo(b2CRequest.getReciepientMobileNo());
        b2C.setSenderMobileNumber(b2CRequest.getSenderMobileNumber());
        b2C = b2CRepo.save(b2C);
        smsRequest.setAmount(b2CRequest.getAmount());
        MB2CResponse mockB2C = new MB2CResponse(b2CRequest);
        MSMSResponse mockSMS = new MSMSResponse(smsRequest);


        baseResponse = (BaseResponse) mappers.toJsonObject(mockB2C.getMockResponse(), BaseResponse.class);


        if (b2C.getId() > 0) {
            smsRequest.setTelcos(b2CRequest.getTelco());
            b2C.setStatusCode(baseResponse.getResponseCode());
            b2C.setStatusMessage(baseResponse.getResponseMessage());
            b2C.setStatusDate(new Date());
            b2CRepo.save(b2C);
            smsRequest.setMobileNumber(b2CRequest.getSenderMobileNumber());
            smsRequest.setSender(true);
            logger.info("Mock Sender SMS: " + mockSMS.getMockResponse());
            baseResponse = (BaseResponse) mappers.toJsonObject(mockSMS.getMockResponse(), BaseResponse.class);
            sendSMS(smsRequest, baseResponse);

            smsRequest.setSender(false);
            smsRequest.setMobileNumber(b2CRequest.getReciepientMobileNo());
            logger.info("Mock Reciever SMS: " + mockSMS.getMockResponse());
            baseResponse = (BaseResponse) mappers.toJsonObject(mockSMS.getMockResponse(), BaseResponse.class);
            sendSMS(smsRequest, baseResponse);

        }
        baseResponse.setData(null);
        baseResponse.setResponseCode(0);
        baseResponse.setResponseMessage("Successfully sent the request.Kindly wait for notification");

        return ResponseEntity.ok().body(baseResponse);
    }

    void sendSMS(SMSRequest smsRequest, BaseResponse baseResponse) {
        SMS_Notifications smsNotifications = new SMS_Notifications();

        StringBuilder sb = new StringBuilder();
        smsRequest.setMobileNumber(smsRequest.getMobileNumber());
        smsNotifications.setMessage(String.valueOf(baseResponse.getData()));
        smsNotifications.setMobileNumber(smsRequest.getMobileNumber());
        smsNotifications = smsRepo.save(smsNotifications);
        if (smsNotifications.getId() > 0)
            updateSMSStatus(smsNotifications);


    }

    private void updateSMSStatus(SMS_Notifications smsNotifications) {
        smsNotifications.setSent(true);
        smsNotifications.setSentDate(new Date());
        smsRepo.save(smsNotifications);
    }


}

package com.example.assessment.dto;

import lombok.Data;

@Data
public class B2CRequest {

    protected String senderMobileNumber;
    protected String narration;
    protected double amount;
    protected String telco;
    protected  String reciepientMobileNo;

}

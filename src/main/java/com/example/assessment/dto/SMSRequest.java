package com.example.assessment.dto;

import lombok.Data;


@Data
public class SMSRequest {

    protected String mobileNumber;
    protected String message;
    protected boolean isSender;
    protected  double amount;
    protected String telcos;

}

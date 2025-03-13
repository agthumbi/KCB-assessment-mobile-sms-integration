package com.example.assessment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B2C_DATA")
public class B2C {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String senderMobileNumber;
    @Lob
    @Basic(fetch = LAZY)
    protected String narration;
    protected double amount;
    protected String telco;
    protected  String reciepientMobileNo;
    @Column(nullable = false)
    protected  int statusCode=-1;
    protected  String statusMessage;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected Date createDate;
    @CreationTimestamp
    protected Date statusDate;


}

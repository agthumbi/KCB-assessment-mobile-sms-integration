package com.example.assessment.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

import static jakarta.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SMS_Notifications")
public class SMS_Notifications {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    protected String mobileNumber;
    @Lob
    @Basic(fetch = LAZY)
    protected String message;
    protected boolean sent;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    protected Date createDate;
    @CreationTimestamp
    protected Date sentDate;
}

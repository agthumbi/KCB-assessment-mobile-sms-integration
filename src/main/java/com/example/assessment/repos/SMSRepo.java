package com.example.assessment.repos;

import com.example.assessment.models.SMS_Notifications;
import org.springframework.data.jpa.repository.JpaRepository;


    public interface SMSRepo extends JpaRepository<SMS_Notifications,Long> {
    }


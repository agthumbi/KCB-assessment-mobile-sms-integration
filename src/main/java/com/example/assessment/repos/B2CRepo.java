package com.example.assessment.repos;

import com.example.assessment.dto.B2CRequest;
import com.example.assessment.models.B2C;
import org.springframework.data.jpa.repository.JpaRepository;

public interface B2CRepo extends JpaRepository<B2C,Long> {
}

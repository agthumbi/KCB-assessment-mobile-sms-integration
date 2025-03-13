package com.example.assessment.controller;


import com.example.assessment.dto.B2CRequest;
import com.example.assessment.services.B2CService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/b2c")
@AllArgsConstructor
@Tag(name = "Mobile Money", description = "Endpoints for sending money using business to customer")
public class B2CController {
   private B2CService b2CService;

    @PostMapping
    @Operation(summary = "B2C Request", description = "Send money to own/other mobile number depending on telco.")
    public ResponseEntity<?> getB2CRequest(@RequestBody B2CRequest b2CRequest) {

        return b2CService.getB2CRequest(b2CRequest);

    }
}

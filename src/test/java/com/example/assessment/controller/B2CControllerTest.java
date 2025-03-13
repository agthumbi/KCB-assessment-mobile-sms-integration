package com.example.assessment.controller;


import com.example.assessment.dto.B2CRequest;
import com.example.assessment.services.B2CService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
//Integeration Test
public class B2CControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testB2CRequest() throws Exception {
        B2CRequest b2CRequest = new B2CRequest();
        ObjectMapper objectMapper=new ObjectMapper();
        b2CRequest.setSenderMobileNumber("254722000000");
        b2CRequest.setReciepientMobileNo("254712000000");
        b2CRequest.setTelco("M-PESA");
        b2CRequest.setAmount(100.00);

        // Act & Assert
        mockMvc.perform(post("/api/v1/b2c")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(b2CRequest)))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.responseCode").value(0))
                .andExpect(jsonPath("$.responseMessage").value("Successfully sent the request.Kindly wait for notification"));

    }
}

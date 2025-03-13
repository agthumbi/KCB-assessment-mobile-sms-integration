package com.example.assessment.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/user")
    public OidcUser userInfo(@AuthenticationPrincipal OidcUser user) {
        return user; // Returns Google profile info as JSON
    }

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint!";
    }
}


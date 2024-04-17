package com.programming.techie.pkce.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/home")
@CrossOrigin(origins = "*")
public class HomeRestController {
	
	@PreAuthorize("hasRole('USER') and hasAuthority('SCOPE_email') "  )
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public String home() {
        return "Hello";
    }
}

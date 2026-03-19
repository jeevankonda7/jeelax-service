package com.nerdcoder.productapp.controller;

import com.nerdcoder.productapp.entity.Users;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication APIs", description = "apis that are authenticated with security")
public class SecurityController {

  private static final Logger log = LoggerFactory.getLogger(SecurityController.class);

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String approveLogin(@RequestBody final Users user) {
    return "user received";
  }

  @GetMapping("/test-security")
  public String testSecurity() {
    return "login successfully";
  }

}


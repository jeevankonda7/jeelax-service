package com.nerdcoder.jeelax.controller;

import com.nerdcoder.jeelax.entity.UserRegistration;
import com.nerdcoder.jeelax.model.AuthRequest;
import com.nerdcoder.jeelax.service.UsersCheckInService;
import com.nerdcoder.jeelax.utils.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private static final Logger log = LoggerFactory.getLogger(AuthController.class);

  private final AuthenticationManager authenticationManager;
  private final JwtUtil jwtUtil;
  private final UsersCheckInService usersCheckInService;

  public AuthController(AuthenticationManager authenticationManager,
                        JwtUtil jwtUtil,
                        UsersCheckInService usersCheckInService) {
    this.authenticationManager = authenticationManager;
    this.jwtUtil = jwtUtil;
    this.usersCheckInService = usersCheckInService;
  }

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String approveAuthentication(@RequestBody final AuthRequest authRequest) {
    try {
      authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                      authRequest.getPassword())
      );
      return jwtUtil.generateToken(authRequest.getUsername());
    } catch (Exception e) {
      return e.getMessage();
    }
  }


  @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String approveSignup(@RequestBody final UserRegistration userRegistrationDetails) {
   return this.usersCheckInService.addUser(userRegistrationDetails);
  }

  @GetMapping("/securitycheck")
  public String approveTest() {
    return "tested";
  }
}

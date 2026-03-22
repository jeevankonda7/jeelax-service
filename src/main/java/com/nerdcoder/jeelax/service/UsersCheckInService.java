package com.nerdcoder.jeelax.service;

import com.nerdcoder.jeelax.entity.UserRegistration;
import com.nerdcoder.jeelax.entity.Users;
import com.nerdcoder.jeelax.repository.RegistrationRepository;
import com.nerdcoder.jeelax.repository.UserDetailsRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsersCheckInService {

  private final UserDetailsRepository usersRepository;
  private final EmailTransferService emailTransferService;
  private final PasswordEncoder passwordEncoder;
  private final RegistrationRepository registrationRepository;

  private static final String INVALID_USERNAME = "Invalid Username";
  private static final String INVALID_PASSWORD = "Invalid Password";
  private static final String INVALID_EMAIL = "Invalid email";
  private static final String INVALID_MOBILENUMBER = "Invalid mobile number";
  private static final String VALID = "valid";

  public UsersCheckInService(UserDetailsRepository usersRepository,
                             EmailTransferService emailTransferService,
                             PasswordEncoder passwordEncoder,
                             RegistrationRepository registrationRepository) {
    this.usersRepository = usersRepository;
    this.emailTransferService = emailTransferService;
    this.passwordEncoder = passwordEncoder;
    this.registrationRepository = registrationRepository;
  }

  public String addUser(UserRegistration userRegistrationDetails) {
    //TODO check duplicate emails
    //TODO check username already present
    //TODO check duplicate mobilenumber
    final String username = userRegistrationDetails.getUsername();
    final String password = userRegistrationDetails.getPassword();
    final String email = userRegistrationDetails.getEmail();
    final String mobileNumber = userRegistrationDetails.getMobileNumber();
    boolean continueFlag = false;
    if (validateUserDetails(username,
            password,
            email,
            mobileNumber).equals(VALID)) {
      Users newUser = Users.builder()
              .username(username)
              .password(passwordEncoder.encode(password))
              .role("ROLE_USER")
              .build();
      newUser = this.usersRepository.save(newUser);
      if (newUser.getId() != null && newUser.getId() > 0) {
        continueFlag = true;
        this.emailTransferService.sendEmail(userRegistrationDetails.getEmail());
      }
    }
    if (continueFlag) {
      userRegistrationDetails = this.registrationRepository.save(userRegistrationDetails);
    }
    return userRegistrationDetails.getId()!=null && userRegistrationDetails.getId()>0 ?
            "signUp successful" :
            "Something went wrong";
  }

  private String validateUserDetails(String username,
                                     String password,
                                     String email,
                                     String mobileNumber) {
    if (!username.trim().matches("^[a-zA-Z0-9_]+$")) {
      return INVALID_USERNAME;
    }
    if (!password.trim().matches("^[^\\s]{8,16}$")) {
      return INVALID_PASSWORD;
    }
    if (!email.trim().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
      return INVALID_EMAIL;
    }
    if (!mobileNumber.trim().matches("^[6-9]\\d{9}$")) {
      return INVALID_MOBILENUMBER;
    }
    return VALID;
  }

  public Users getUser(final Users user) {
    return usersRepository.findByUsername(user.getUsername()).get();
  }
}

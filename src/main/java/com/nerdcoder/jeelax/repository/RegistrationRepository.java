package com.nerdcoder.jeelax.repository;

import com.nerdcoder.jeelax.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<UserRegistration, Integer> {

}

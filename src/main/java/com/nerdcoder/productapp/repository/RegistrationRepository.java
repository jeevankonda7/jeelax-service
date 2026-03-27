package com.nerdcoder.productapp.repository;

import com.nerdcoder.productapp.entity.UserRegistration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<UserRegistration, Integer> {

}

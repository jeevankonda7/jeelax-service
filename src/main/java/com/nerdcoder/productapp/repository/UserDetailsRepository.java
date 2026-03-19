package com.nerdcoder.productapp.repository;

import com.nerdcoder.productapp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<Users, Integer> {

  Optional<Users> findByUsername(String username);
}

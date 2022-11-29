package com.devopsApp.ticketingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devopsApp.ticketingSystem.model.UserReg;

@Repository
public interface UserRegRepository extends JpaRepository<UserReg, Integer>{
 public List<UserReg> findByEmailId(String emailId);
}

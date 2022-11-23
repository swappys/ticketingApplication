package com.devopsApp.ticketingSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devopsApp.ticketingSystem.model.UserReg;
import com.devopsApp.ticketingSystem.model.UserTicket;
import com.devopsApp.ticketingSystem.model.UserTicketNew;
@Repository
public interface UserTicketRepository extends JpaRepository<UserTicketNew, Integer> {
	 public List<UserTicketNew> findByUserId(int userId);
	 public List<UserTicketNew> findByTicketId(int ticketId);

}

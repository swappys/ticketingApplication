package com.devopsApp.ticketingSystem.Service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.devopsApp.ticketingSystem.model.UserReg;
import com.devopsApp.ticketingSystem.model.UserTicketNew;

import lombok.extern.apachecommons.CommonsLog;


public interface TicketingService {
	ResponseEntity<UserReg> registerUser(UserReg userReg);	
	ResponseEntity<UserReg> validateUser(UserReg userReg);
	ResponseEntity<UserTicketNew> createTicket(UserTicketNew userTicket);
	ResponseEntity<UserTicketNew> getTicketsForUser(UserTicketNew userTicket);
	ResponseEntity<UserTicketNew> getAllTickets();
	String deleteTicket( int tid);
	String updateTicket( int tid,  UserTicketNew userTicketNew);
	ResponseEntity<UserTicketNew> getTicket(UserTicketNew userTicketNew);
}

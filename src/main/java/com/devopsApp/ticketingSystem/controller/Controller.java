package com.devopsApp.ticketingSystem.controller;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.devopsApp.ticketingSystem.Service.TicketingService;
import com.devopsApp.ticketingSystem.model.UserReg;
import com.devopsApp.ticketingSystem.model.UserTicket;
import com.devopsApp.ticketingSystem.model.UserTicketNew;
import com.devopsApp.ticketingSystem.repository.UserRegRepository;
import com.devopsApp.ticketingSystem.repository.UserTicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.OptionalLongDeserializer;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
@Autowired
private TicketingService ticketingService;

@Autowired
private UserRegRepository userRegRepository;
@Autowired
private UserTicketRepository userTicketRepository;

@CrossOrigin(origins = "http://localhost:8080")
@PostMapping("/registerUser")
private ResponseEntity<UserReg> registerUser(@RequestBody UserReg user) {	
	ResponseEntity<UserReg>entityResult=ticketingService.registerUser(user);
	return entityResult;

}
@CrossOrigin(origins = "http://localhost:8080")
@PostMapping("/validateUser")
private ResponseEntity<UserReg> validateUser(@RequestBody UserReg user) {
	ResponseEntity<UserReg>entityResult=ticketingService.validateUser(user);
	return entityResult;
}

@PostMapping("/createTicket")
private ResponseEntity<UserTicketNew> createTicket(@RequestBody UserTicketNew userTicket) {
	ResponseEntity<UserTicketNew>entityResult=ticketingService.createTicket(userTicket);
	return entityResult;
	
}

@PostMapping("/getTicketsForUser")
private ResponseEntity<UserTicketNew> getTicketsForUser(@RequestBody UserTicketNew userTicket) {
	ResponseEntity<UserTicketNew>entityResult= ticketingService.getTicketsForUser(userTicket);
	return entityResult;
}

@GetMapping("/getAllTickets")
private ResponseEntity<UserTicketNew> getAllTickets() {
	ResponseEntity<UserTicketNew>entityResultt = ticketingService.getAllTickets();
	return entityResultt;
}

@DeleteMapping("/deleteTicket/{tid}")
private String deleteTicket(@PathVariable int tid) {
	String result = ticketingService.deleteTicket(tid);
	return result;
}

@PutMapping("/updateTicket/{tid}")
private String updateTicket(@PathVariable int tid, @RequestBody UserTicketNew userTicketNew) { 	
	String result = ticketingService.updateTicket(tid, userTicketNew);
	return result;
}
@PostMapping("/getTicket")
private ResponseEntity<UserTicketNew> getTicket( @RequestBody UserTicketNew userTicketNew) {
	ResponseEntity<UserTicketNew>entityResultt = ticketingService.getTicket(userTicketNew);
	return entityResultt;
}
}



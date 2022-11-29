package com.devopsApp.ticketingSystem.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devopsApp.ticketingSystem.model.UserReg;
import com.devopsApp.ticketingSystem.model.UserTicketNew;
import com.devopsApp.ticketingSystem.repository.UserRegRepository;
import com.devopsApp.ticketingSystem.repository.UserTicketRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class TicketingServiceImpl implements TicketingService{

	@Autowired
	private UserRegRepository userRegRepository;
	
	@Autowired
	private UserTicketRepository userTicketRepository;
	

	@Override
	public ResponseEntity<UserReg> registerUser(UserReg user) {
		UserReg res = new UserReg();
		HttpHeaders headers = new HttpHeaders();
		try {
		user.setUserType(0);
		userRegRepository.save(user);
		}
		catch(Exception ex) {
			res.setStatus("FAILURE");
			ResponseEntity<UserReg> entityFailure = new ResponseEntity<UserReg>(res,headers,HttpStatus.CREATED);
			return entityFailure;
		}
		res.setStatus("SUCCESS");
		 ResponseEntity<UserReg> entitySuccess = new ResponseEntity<UserReg>(res,headers,HttpStatus.CREATED);
		 return entitySuccess;
	
	}

	@Override
	public ResponseEntity<UserReg> validateUser(UserReg user) {
		UserReg res = new UserReg();
		HttpHeaders headers = new HttpHeaders();
		List<UserReg> fetchedUser = new ArrayList<UserReg>();
		String validated= "You have entered wrong credentials";
	try {
		
		fetchedUser = userRegRepository.findByEmailId(user.getEmailId());
		for(UserReg userReg:fetchedUser) {
		String password = userReg.getPassword();
		int userId = userReg.getId();
		int userType = userReg.getUserType();
		if(password.equals(user.getPassword())) {
			res.setStatus("SUCCESS");
			res.setUserType(userType);
			res.setId(userId);
		}
		else {
			res.setStatus("FAILURE");
			res.setMessage("Incorrect Password");
			ResponseEntity<UserReg> entityFailure = new ResponseEntity<UserReg>(res,headers,HttpStatus.CREATED);
			return entityFailure;
		}
		}
	}catch(Exception ex) {
		res.setStatus("FAILURE");
		ResponseEntity<UserReg> entityFailure = new ResponseEntity<UserReg>(res,headers,HttpStatus.CREATED);
		return entityFailure;
	}
	ResponseEntity<UserReg> entitySuccess = new ResponseEntity<UserReg>(res,headers,HttpStatus.CREATED);
	return entitySuccess;
	}

	@Override
	public ResponseEntity<UserTicketNew> createTicket(UserTicketNew userTicket) {
		UserTicketNew res = new UserTicketNew();
		HttpHeaders headers = new HttpHeaders();
		try {
			userTicketRepository.save(userTicket);
		}catch(Exception ex) {
			res.setStatus1("FAILURE");
			ex.printStackTrace();
			ResponseEntity<UserTicketNew> entityFailure = new ResponseEntity<UserTicketNew>(res,headers,HttpStatus.CREATED);
			return entityFailure;
		}
		res.setStatus1("SUCCESS");
		ResponseEntity<UserTicketNew> entitySuccess = new ResponseEntity<UserTicketNew>(res,headers,HttpStatus.CREATED);
		return entitySuccess;
	}

	@Override
	public  ResponseEntity<UserTicketNew> getTicketsForUser(UserTicketNew userTicket) {
		List<UserTicketNew> fetchedTickets = new ArrayList<UserTicketNew>();
		UserTicketNew userTicketNew = new UserTicketNew();
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		HttpHeaders headers = new HttpHeaders();
		try {
			fetchedTickets=userTicketRepository.findByUserId(userTicket.getUserId());
			userTicketNew.setTickets(fetchedTickets);
		}catch(Exception ex) {
			userTicket.setStatus1("FAILURE");
			ex.printStackTrace();
			ResponseEntity<UserTicketNew> entityFailure = new ResponseEntity<UserTicketNew>(userTicket,headers,HttpStatus.CREATED);
			return entityFailure;
		}
		userTicketNew.setStatus1("SUCCESS");
		ResponseEntity<UserTicketNew> entitySuccess = new ResponseEntity<UserTicketNew>(userTicketNew,headers,HttpStatus.CREATED);
		return entitySuccess;
	}

	@Override
	public ResponseEntity<UserTicketNew> getAllTickets() {
		
		List<UserTicketNew> getAllTickets = new ArrayList<UserTicketNew>();
		UserTicketNew userTicketNew = new UserTicketNew();
		String json = null;
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		HttpHeaders headers = new HttpHeaders();
	    try {
	    	getAllTickets = userTicketRepository.findAll();
			userTicketNew.setTickets(getAllTickets);
	    }catch(Exception ex) {
	    	userTicketNew.setStatus1("FAILURE");
			ex.printStackTrace();
			ResponseEntity<UserTicketNew> entityFailure = new ResponseEntity<UserTicketNew>(userTicketNew,headers,HttpStatus.CREATED);
			return entityFailure;
	    }
		userTicketNew.setStatus1("SUCCESS");
		ResponseEntity<UserTicketNew> entitySuccess = new ResponseEntity<UserTicketNew>(userTicketNew,headers,HttpStatus.CREATED);
		return entitySuccess;
	}

	@Override
	public String deleteTicket(int tid) {
		try {
			@SuppressWarnings("deprecation")
			UserTicketNew fetchedTicket = userTicketRepository.getOne(tid);
			if(fetchedTicket.getTicketType() != null) {
			userTicketRepository.delete(fetchedTicket);
			}
			else {
				return "No record found to be deleted";
			}
			}
			catch(Exception ex){
				ex.printStackTrace();
				return "No record found to be deleted";
			}
			return "Success";
	}

	@Override
	public String updateTicket(int tid, UserTicketNew userTicketNew) {
		String json=null;
		try {
			UserTicketNew oldData = userTicketRepository.getOne(tid);
			oldData.setStatus(userTicketNew.getStatus());
			oldData.setTicketType(userTicketNew.getTicketType());
			oldData.setU_comments(userTicketNew.getU_comments());
			oldData.setA_comments(userTicketNew.getA_comments());
			userTicketRepository.save(oldData);
		}catch(Exception ex){
			return "Failure";
		}
		return "Success";
	}

//	@Override
//	public ResponseEntity<UserTicketNew> getTicket(UserTicketNew userTicketNew) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public ResponseEntity<UserTicketNew> getTicket(UserTicketNew userTicket) {
		List<UserTicketNew> getTicket = new ArrayList<UserTicketNew>();
		UserTicketNew userTicketNew = new UserTicketNew();
		String json = null;
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.enable(SerializationFeature.INDENT_OUTPUT);
		HttpHeaders headers = new HttpHeaders();
	    try {
	    	getTicket = userTicketRepository.findByTicketId(userTicket.getTicketId());
	    	userTicketNew.setTickets(getTicket);

	    }catch(Exception ex) {
	    	userTicketNew.setStatus1("FAILURE");
			ex.printStackTrace();
			ResponseEntity<UserTicketNew> entityFailure = new ResponseEntity<UserTicketNew>(userTicketNew,headers,HttpStatus.CREATED);
			return entityFailure;
	    }
		userTicketNew.setStatus1("SUCCESS");
		ResponseEntity<UserTicketNew> entitySuccess = new ResponseEntity<UserTicketNew>(userTicketNew,headers,HttpStatus.CREATED);
		return entitySuccess;

	}
}





package com.devopsApp.ticketingSystem.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "userTicket_new")
public class UserTicketNew {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ticketId;

	@Column(name="ticketType")
	private String ticketType;

	@Column(name="status")
	private int status;

	@Column(name = "u_comments")
	private String u_comments;

	@Column(name = "a_comments")
	private String a_comments;

	@Column(name = "ticketNumber")
	private String ticketNumber;
	
	@Column(name = "u_id")
	private int userId;

	@Column(name ="emailId")
	private String emailId;	
	
	@Transient
	private String status1;
	
	@Transient
	private List<UserTicketNew> tickets;
	
	public UserTicketNew() {
		super();
	}
	public UserTicketNew(int ticketId, String ticketType, int status, String u_comments, String a_comments,
			String ticketNumber, int userId, String emailId, String status1, List<UserTicketNew> tickets) {
		super();
		this.ticketId = ticketId;
		this.ticketType = ticketType;
		this.status = status;
		this.u_comments = u_comments;
		this.a_comments = a_comments;
		this.ticketNumber = ticketNumber;
		this.userId = userId;
		this.emailId = emailId;
		this.status1 = status1;
		this.tickets = tickets;
	}
	public List<UserTicketNew> getTickets() {
		return tickets;
	}
	public void setTickets(List<UserTicketNew> tickets) {
		this.tickets = tickets;
	}
	public String getStatus1() {
		return status1;
	}
	public void setStatus1(String status1) {
		this.status1 = status1;
	}
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getU_comments() {
		return u_comments;
	}

	public void setU_comments(String u_comments) {
		this.u_comments = u_comments;
	}

	public String getA_comments() {
		return a_comments;
	}

	public void setA_comments(String a_comments) {
		this.a_comments = a_comments;
	}

	public String getTicketNumber() {
		return ticketNumber;
	}

	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
}

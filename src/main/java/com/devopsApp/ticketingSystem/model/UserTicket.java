package com.devopsApp.ticketingSystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "userTicket")
public class UserTicket {
@Id
private int userId;

@Column(name ="emailId")
private String emailId;

@OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)
@JoinColumn(name = "uId",referencedColumnName = "userId")
private List<Ticket>ticket;

@Transient
private String status;

public UserTicket() {
	super();
}



public UserTicket(int userId, String emailId, List<Ticket> ticket, String status) {
	super();
	this.userId = userId;
	this.emailId = emailId;
	this.ticket = ticket;
	this.status = status;
}



public String getStatus() {
	return status;
}



public void setStatus(String status) {
	this.status = status;
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

public List<Ticket> getTicket() {
	return ticket;
}

public void setTicket(List<Ticket> ticket) {
	this.ticket = ticket;
}

}

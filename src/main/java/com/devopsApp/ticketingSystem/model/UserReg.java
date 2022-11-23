package com.devopsApp.ticketingSystem.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="userReg", uniqueConstraints = @UniqueConstraint(columnNames = "emailId"))
public class UserReg {
	@Column(name = "userId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "userType")
	private int userType;
	
	@Transient
	private String status;
	
	@Transient
	private String message;
//	@OneToMany(targetEntity = Ticket.class, cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id", referencedColumnName = "id")
//	private List<Ticket> tickets;

	public UserReg() {
		super();
	}
	
	public UserReg(int id, String emailId, String name, String phoneNumber, String password, int userType,
			String status, String message) {
		super();
		this.id = id;
		this.emailId = emailId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.userType = userType;
		this.status = status;
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	

}

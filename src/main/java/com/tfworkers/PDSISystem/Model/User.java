package com.tfworkers.PDSISystem.Model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
public class User {
	 @Id
	 @Column(nullable = false)
	 @GeneratedValue(strategy = GenerationType.IDENTITY )
	 private long id; //User ID
	 @Column(nullable = false)
	 private String firstName; //User First Name
	 @Column(nullable = false)
	 private String lastName;//User Last Name
	 @Column(nullable = false,unique = true)
	 private String email;//User email
	 private int age;//User age
	 @Column(nullable = false)
	 private String password; //User Password
	 private boolean deleteStatus = false;
	 @Column(nullable = true)
	 private String phoneNumber; //User Phone Number
	 private String createdDate; //User Created Date  Set by System
	 private String updatedDate;//User Updated Date   Set by System
	 
}

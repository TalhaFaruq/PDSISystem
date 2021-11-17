package com.tfworkers.PDSISystem.Model.Entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * The type User.
 */
@Table(name = "user", indexes = {
		@Index(name = "created_date_index", columnList = "createdDate"),
		@Index(name = "active_index", columnList = "isActive")
})
@Data
@Entity
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id; // User ID
	@NotBlank(message = "Username is Mandatory")
	private String username;
	@NotBlank(message = "First name is Mandatory")
	private String firstName; // User First Name
	@NotBlank(message = "last name is Mandatory")
	private String lastName;// User Last Name
	@NotBlank(message = "Email is Mandatory")
	@Column(nullable = false, unique = true)
	private String email;// User email
	@Min(value=1, message = "age should be greater than 18")
	private int age;// User age
	private Long cnic;
	private String password; // User Password
	private boolean isActive = true; //if deleted than it will be false
	private String phoneNumber; // User Phone Number
	private Date createdDate; // User Created Date Set by System
	private Date updatedDate; // User Updated Date Set by System
	private String allowance;
	private String post;
	private String department;
	private int token;
	private boolean accountVerifyStatus = false;
	private Date expirationDate;
	private int warning;


	@ManyToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
	private List<Project> projects = new ArrayList<>();

	@ManyToMany(targetEntity = Tags.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private List<Tags> tags = new ArrayList<>();

}

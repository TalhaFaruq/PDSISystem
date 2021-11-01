package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id; // User ID
	@Column(nullable = false)
	private String firstName; // User First Name
	@Column(nullable = false)
	private String lastName;// User Last Name
	@Column(nullable = false, unique = true)
	private String email;// User email
	private int age;// User age
	@Column(nullable = false)
	private String password; // User Password
	private boolean isActive = false;
	@Column(nullable = true)
	private String phoneNumber; // User Phone Number
	@Column(nullable = false, unique = true)
	private Date createdDate; // User Created Date Set by System
	private Date updatedDate; // User Updated Date Set by System
	private String allowance;
	private String post;
	private String department;
	@Column(nullable = false, unique = true)
	private int token;
	private boolean accountVerifyStatus = false;
	private Date expirationDate;

	@ManyToMany(targetEntity = Project.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<Project> projects = new ArrayList<Project>();

	@ManyToMany(targetEntity = Tags.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private List<Tags> tags = new ArrayList<Tags>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getAllowance() {
		return allowance;
	}

	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public boolean isAccountVerifyStatus() {
		return accountVerifyStatus;
	}

	public void setAccountVerifyStatus(boolean accountVerifyStatus) {
		this.accountVerifyStatus = accountVerifyStatus;
	}

	
}

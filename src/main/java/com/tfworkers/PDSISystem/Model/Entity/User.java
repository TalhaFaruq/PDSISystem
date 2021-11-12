package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

/**
 * The type User.
 */
@Entity
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id; // User ID
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
	private List<Project> projects = new ArrayList<Project>();

	@ManyToMany(targetEntity = Tags.class,cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	private List<Tags> tags = new ArrayList<Tags>();


	/**
	 * Gets cnic.
	 *
	 * @return the cnic
	 */
	public Long getCnic() {
		return cnic;
	}

	/**
	 * Sets cnic.
	 *
	 * @param cnic the cnic
	 */
	public void setCnic(Long cnic) {
		this.cnic = cnic;
	}

	/**
	 * Gets user id.
	 *
	 * @return the user id
	 */
	public long getUser_id() {
		return user_id;
	}

	/**
	 * Sets user id.
	 *
	 * @param id the id
	 */
	public void setUser_id(long id) {
		this.user_id = id;
	}

	/**
	 * Gets first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets first name.
	 *
	 * @param firstName the first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets last name.
	 *
	 * @param lastName the last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets email.
	 *
	 * @param email the email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets age.
	 *
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * Sets age.
	 *
	 * @param age the age
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Gets password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets password.
	 *
	 * @param password the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Is active boolean.
	 *
	 * @return the boolean
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * Sets active.
	 *
	 * @param isActive the is active
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Gets phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Sets phone number.
	 *
	 * @param phoneNumber the phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets created date.
	 *
	 * @return the created date
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * Sets created date.
	 *
	 * @param createdDate the created date
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets updated date.
	 *
	 * @return the updated date
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * Sets updated date.
	 *
	 * @param updatedDate the updated date
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets allowance.
	 *
	 * @return the allowance
	 */
	public String getAllowance() {
		return allowance;
	}

	/**
	 * Sets allowance.
	 *
	 * @param allowance the allowance
	 */
	public void setAllowance(String allowance) {
		this.allowance = allowance;
	}

	/**
	 * Gets post.
	 *
	 * @return the post
	 */
	public String getPost() {
		return post;
	}

	/**
	 * Sets post.
	 *
	 * @param post the post
	 */
	public void setPost(String post) {
		this.post = post;
	}

	/**
	 * Gets department.
	 *
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Sets department.
	 *
	 * @param department the department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Gets projects.
	 *
	 * @return the projects
	 */
	public List<Project> getProjects() {
		return projects;
	}

	/**
	 * Sets projects.
	 *
	 * @param projects the projects
	 */
	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	/**
	 * Gets tags.
	 *
	 * @return the tags
	 */
	public List<Tags> getTags() {
		return tags;
	}

	/**
	 * Sets tags.
	 *
	 * @param tags the tags
	 */
	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	/**
	 * Gets token.
	 *
	 * @return the token
	 */
	public int getToken() {
		return token;
	}

	/**
	 * Sets token.
	 *
	 * @param token the token
	 */
	public void setToken(int token) {
		this.token = token;
	}

	/**
	 * Gets expiration date.
	 *
	 * @return the expiration date
	 */
	public Date getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets expiration date.
	 *
	 * @param expirationDate the expiration date
	 */
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Is account verify status boolean.
	 *
	 * @return the boolean
	 */
	public boolean isAccountVerifyStatus() {
		return accountVerifyStatus;
	}

	/**
	 * Sets account verify status.
	 *
	 * @param accountVerifyStatus the account verify status
	 */
	public void setAccountVerifyStatus(boolean accountVerifyStatus) {
		this.accountVerifyStatus = accountVerifyStatus;
	}

	/**
	 * Gets warning.
	 *
	 * @return the warning
	 */
	public int getWarning() {
		return warning;
	}

	/**
	 * Sets warning.
	 *
	 * @param warning the warning
	 */
	public void setWarning(int warning) {
		this.warning = warning;
	}
}

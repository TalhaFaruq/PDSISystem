package com.tfworkers.PDSISystem.Model.Entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

//@NamedNativeQuery(name = "UserRepository.recommendedManagers",
//		query = "user.user_id, user.first_name, user.department FROM user JOIN tags ON tags.name = 'Administrative' and user.post = 'Manager'",
//		resultSetMapping ="Mapping.RecommendedManagerDTO")
//@SqlResultSetMapping(name = "Mapping.RecommendedManagerDTO",
//		classes = @ConstructorResult(targetClass = RecommendedManagerDTO.class,
//									columns = {@ColumnResult(name = "id"),
//									@ColumnResult(name = "first_name"),
//											@ColumnResult(name = "department")}))

@Entity
public class User {
	@Id
	@Column(nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long user_id; // User ID
	private String firstName; // User First Name
	private String lastName;// User Last Name
	@Column(nullable = false, unique = true)
	private String email;// User email
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


	public Long getCnic() {
		return cnic;
	}

	public void setCnic(Long cnic) {
		this.cnic = cnic;
	}

	public long getUser_id() {
		return user_id;
	}

	public void setUser_id(long id) {
		this.user_id = id;
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

	public int getWarning() {
		return warning;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}
}

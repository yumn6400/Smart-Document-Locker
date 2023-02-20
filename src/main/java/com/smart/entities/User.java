package com.smart.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name="USER")
public class User implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int userId;
	
	@NotBlank(message="Username should not be empty")
	@Size(min=3,max=35,message="Username must be in between 3-35 characters")
	private String name;
	 
	private String role;
	

	private String password;
	
	
	@Column(unique=true)
	@Email(regexp="^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
	private String email;
	
	
	@Column(unique=true)
	private String phone;
	
	
	private boolean agreed;
	
	@OneToMany(cascade=CascadeType.ALL ,fetch=FetchType.LAZY , mappedBy="user",orphanRemoval=true)
	private List<Bucket> bucketList=new ArrayList<>();
	
	
	public List<Bucket> getBucketList() {
		return bucketList;
	}
	public void setBucketList(List<Bucket> bucketList) {
		this.bucketList = bucketList;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isAgreed() {
		return agreed;
	}
	public void setAgreed(boolean agreed) {
		this.agreed = agreed;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int userId, String name, String role,String password, String email, String phone, boolean agreed,
			List<Bucket> bucketList) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.agreed = agreed;
		this.role=role;
		this.bucketList = bucketList;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", role=" + role + ", password=" + password + ", email="
				+ email + ", phone=" + phone + ", agreed=" + agreed + ", bucketList=" + bucketList + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.userId==((Bucket)obj).getBucketId();	
	}

		

}

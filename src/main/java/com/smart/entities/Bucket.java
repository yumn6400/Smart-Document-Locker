package com.smart.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BUCKET")
public class Bucket implements java.io.Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int bucketId;
	@Column(unique=true)
	private String name;
	private String imageURL;
	private String description;
	
	@ManyToOne
	private User user=new  User();

	public int getBucketId() {
		return bucketId;
	}

	public void setBucketId(int bucketId) {
		this.bucketId = bucketId;
	}

	public String getImageURL() {
		return imageURL;
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Bucket() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Bucket(int bucketId, String name,String imageURL, String description, User user) {
		super();
		this.bucketId = bucketId;
		this.name=name;
		this.imageURL = imageURL;
		this.description = description;
		this.user = user;
	}

	@Override
	public String toString() {
		return "Bucket [bucketId=" + bucketId + ", name=" + name + ", imageURL=" + imageURL + ", description="
				+ description + ", user=" + user + "]";
	}

	
	
	
	
	
	
	

}

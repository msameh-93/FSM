package com.cegedim.fsm.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FileModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message= "Please Provide file name")
	private String filename;
	@NotBlank(message= "Please provide a description for the file")
	private String description;
	private String path;
	private String serial;
	@JsonIgnore
	private String originalFilename;
//	@Lob
//	private byte[] file;
	
	//file-user relationship
	private String userOwner;
	@ManyToOne
	@JoinColumn(name= "user_id", updatable= false, nullable= false)
	@JsonIgnore
	private User user;
	/************Dates************/
	@Column(updatable= false, nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date uploadedDate;
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date updatedAt;
	@PrePersist
	public void uploadDate() {
		this.uploadedDate= new Date();
	}
	@PreUpdate
	public void update() {
		this.updatedAt= new Date();
	}
	
	public FileModel() {

	}
	public FileModel(@NotBlank(message = "Please Provide file name") String filename,
			@NotBlank(message = "Please provide a description for the file") String description,
			@NotBlank(message= "Please provide a valid file") String originaFilename/*, byte[] file*/) {
		super();
		this.filename = filename;
		this.description = description;
		this.originalFilename= originaFilename;
//		this.file = file;
	}
	/****Getters and Setters***/
	public String getFilename() {
		return filename;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getUploadedDate() {
		return uploadedDate;
	}
	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getUserOwner() {
		return userOwner;
	}
	public void setUserOwner(String userOwner) {
		this.userOwner = userOwner;
	}
//	public byte[] getFile() {
//		return file;
//	}
//	public void setFile(byte[] file) {
//		this.file = file;
//	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
	}
	public String getOriginalFilename() {
		return originalFilename;
	}
	public void setOriginalFilename(String originalFilename) {
		this.originalFilename = originalFilename;
	}
	
}

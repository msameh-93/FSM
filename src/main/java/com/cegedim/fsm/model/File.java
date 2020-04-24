package com.cegedim.fsm.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	/*
	 * Serial Number assigned from user name which will be unique
	 */
	@NotBlank(message= "Please Provide file name")
	private String filename;
	@NotBlank(message= "Please provide a description for the file")
	private String description;
		
	//file-user relationship
	
	@Column(updatable= false, nullable = false)
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date uploadedDate;
	@JsonFormat(pattern= "yyyy-mm-dd")
	private Date updatedAt;
	@PrePersist
	public void uploadDate() {
		this.uploadedDate= new Date();
	}
	@PreUpdate
	public void update() {
		this.updatedAt= new Date();
	}
	/********************************/
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
}

package com.cegedim.fsm.dto;

import java.util.Date;

import com.cegedim.fsm.entities.FileModel;
import com.fasterxml.jackson.annotation.JsonFormat;

public class FileDTO {
	private Long id;
	private String serial;
	private String filename;
	private String description;
	@JsonFormat(pattern= "yyyy-MM-dd")
	private Date uploadDate;
	
	public static FileDTO convertoToDTO(FileModel file) {
		FileDTO fileDTO= new FileDTO();
		
		fileDTO.id= file.getId();
		fileDTO.serial= file.getSerial();
		fileDTO.filename= file.getFilename();
		fileDTO.description= file.getDescription();
		fileDTO.uploadDate= file.getUploadedDate();
		return fileDTO;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFilename() {
		return filename;
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

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	@Override
	public String toString() {
		return "~~~~~FileDTO [id=" + id + ", serial=" + serial + ", filename=" + filename + ", description=" + description
				+ ", uploadDate=" + uploadDate + "]";
	}

}

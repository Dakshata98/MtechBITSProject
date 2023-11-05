package com.scheduled.mailing.dto;

import java.util.Date;

public class MailOutputData {
	
	private Integer id;
    private String recipient;
    private String subject;
    private Date scheduledDateTime;
    private String status;
    
	public MailOutputData() {
		super();
	}

	public MailOutputData(Integer id, String recipient, String subject, Date scheduledDateTime, String status) {
		super();
		this.id = id;
		this.recipient = recipient;
		this.subject = subject;
		this.scheduledDateTime = scheduledDateTime;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

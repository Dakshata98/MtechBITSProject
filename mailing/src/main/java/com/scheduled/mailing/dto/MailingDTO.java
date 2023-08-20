package com.scheduled.mailing.dto;

public class MailingDTO {
	
	private Integer id;
    private MailBody body;
    private String recipient;
    private String sender;
    private String scheduledDateTime;
    
	public MailingDTO() {
		super();
	}

	public MailingDTO(Integer id, MailBody body, String recipient, String sender, String scheduledDateTime) {
		super();
		this.id = id;
		this.body = body;
		this.recipient = recipient;
		this.sender = sender;
		this.scheduledDateTime = scheduledDateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MailBody getBody() {
		return body;
	}

	public void setBody(MailBody body) {
		this.body = body;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(String scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}
    
	
}

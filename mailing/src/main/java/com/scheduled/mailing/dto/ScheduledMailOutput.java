package com.scheduled.mailing.dto;

import java.util.Date;

public class ScheduledMailOutput {
	
	private Integer id;
    private String sender;
    private String recipient;
    private Date createDateTime;
    private Date lastUpdateDateTime;
    private Date scheduledDateTime;
    private Body mailBody;
    private byte[] attachment;
    private String status;
    
	public ScheduledMailOutput() {
		super();
	}

	public ScheduledMailOutput(Integer id, String sender, String recipient, Date createDateTime,
			Date lastUpdateDateTime, Date scheduledDateTime, Body mailBody, byte[] attachment, String status) {
		super();
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.createDateTime = createDateTime;
		this.lastUpdateDateTime = lastUpdateDateTime;
		this.scheduledDateTime = scheduledDateTime;
		this.mailBody = mailBody;
		this.attachment = attachment;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getLastUpdateDateTime() {
		return lastUpdateDateTime;
	}

	public void setLastUpdateDateTime(Date lastUpdateDateTime) {
		this.lastUpdateDateTime = lastUpdateDateTime;
	}

	public Date getScheduledDateTime() {
		return scheduledDateTime;
	}

	public void setScheduledDateTime(Date scheduledDateTime) {
		this.scheduledDateTime = scheduledDateTime;
	}

	public Body getMailBody() {
		return mailBody;
	}

	public void setMailBody(Body mailBody) {
		this.mailBody = mailBody;
	}

	public byte[] getAttachment() {
		return attachment;
	}

	public void setAttachment(byte[] attachment) {
		this.attachment = attachment;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

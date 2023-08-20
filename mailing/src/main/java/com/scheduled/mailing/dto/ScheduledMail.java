package com.scheduled.mailing.dto;

import java.sql.Blob;
import java.sql.Clob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "SCHMAIL")
public class ScheduledMail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "SENDER")
    private String sender;

    @Column(name = "RECIPIENT")
    private String recipient;

    @Column(name = "CREATE_DATETIME")
    private Date createDateTime;

    @Column(name = "LSTUPD_DATETIME")
    private Date lastUpdateDateTime;

    @Column(name = "SCH_DATETIME")
    private Date scheduledDateTime;

    @Column(name = "MAIL_BODY", columnDefinition = "VARCHAR(MAX)")
    private String mailBody;

    @Lob
    @Column(name = "ATTACHMENT")
    private byte[] attachment;

    @Column(name = "STATUS")
    private String status;

	public ScheduledMail() {
		super();
	}

	public ScheduledMail(Integer id, String sender, String recipient, Date createDateTime, Date lastUpdateDateTime,
			Date scheduledDateTime, String mailBody, byte[] attachment, String status) {
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

	public String getMailBody() {
		return mailBody;
	}

	public void setMailBody(String mailBody) {
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

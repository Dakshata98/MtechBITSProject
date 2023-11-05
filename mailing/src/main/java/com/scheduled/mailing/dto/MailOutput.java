package com.scheduled.mailing.dto;

import java.util.Date;
import java.util.List;

public class MailOutput {
	
	private String mailId;
	private List<MailOutputData> mails;
	
	public MailOutput() {
		super();
	}
	
	public MailOutput(String mailId, List<MailOutputData> mails) {
		super();
		this.mailId = mailId;
		this.mails = mails;
	}

	public String getMailId() {
		return mailId;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	public List<MailOutputData> getMails() {
		return mails;
	}

	public void setMails(List<MailOutputData> mails) {
		this.mails = mails;
	}

}

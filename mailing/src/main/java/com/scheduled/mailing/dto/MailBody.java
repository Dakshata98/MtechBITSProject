package com.scheduled.mailing.dto;

import java.util.List;

public class MailBody {
	
    private String subject;
    private String salutation;
    private List<StringBuffer> stanzas;
    private String complimentaryClose;
    private String signature;
    
	public MailBody() {
		super();
	}

	public MailBody(String subject, String salutation, List<StringBuffer> stanzas, String complimentaryClose,
			String signature) {
		super();
		this.subject = subject;
		this.salutation = salutation;
		this.stanzas = stanzas;
		this.complimentaryClose = complimentaryClose;
		this.signature = signature;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public List<StringBuffer> getStanzas() {
		return stanzas;
	}

	public void setStanzas(List<StringBuffer> stanzas) {
		this.stanzas = stanzas;
	}

	public String getComplimentaryClose() {
		return complimentaryClose;
	}

	public void setComplimentaryClose(String complimentaryClose) {
		this.complimentaryClose = complimentaryClose;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

}

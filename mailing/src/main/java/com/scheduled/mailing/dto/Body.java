package com.scheduled.mailing.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Body {
	@XmlElement(name = "subject")
    private String subject;
	@XmlElement(name = "salutation")
    private String salutation;
    @XmlElementWrapper(name = "stanzas")
    @XmlElement(name = "stanza")
    private List<StringBuffer> stanzas;
    @XmlElement(name = "complimentaryClose")
    private String complimentaryClose;
    @XmlElement(name = "signature")
    private String signature;
    
	public Body() {
		super();
	}

	public Body(String subject, String salutation, List<StringBuffer> stanzas, String complimentaryClose,
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
